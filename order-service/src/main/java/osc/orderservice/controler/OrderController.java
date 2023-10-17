package osc.orderservice.controler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import osc.orderservice.client.ProductFeignClient;
import osc.orderservice.entity.Item;
import osc.orderservice.entity.Order;
import osc.orderservice.headergenerator.HeaderGenerator;
import osc.orderservice.mapper.UserMapper;
import osc.orderservice.security.AuthHelper;
import osc.orderservice.service.CartService;
import osc.orderservice.service.OrderService;
import osc.orderservice.utilities.OrderUtilities;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private HeaderGenerator headerGenerator;

    @Autowired
    private ProductFeignClient productFeignClient;

    private UserMapper userMapper;

    private AuthHelper authHelper;

    public OrderController (AuthHelper authHelper) {
        this.authHelper = authHelper;
    }

    @PostMapping()
    public ResponseEntity <Order> saveOrder(
            @RequestHeader(value = "Cookie") String cartId)
            //HttpServletRequest request)
    {
        String userId = authHelper.getUserId ();
        List <Item> cart = cartService.getAllItemsFromCart(cartId);
        if(cart != null && userId != null) {
            Order order = this.createOrder(cart, userId);
            try{
                orderService.saveOrder(order);
                productFeignClient.updateProductShipOut (orderService.getProductsFromOrder(order));
                cartService.deleteCart(cartId);
                return new ResponseEntity<Order>(
                        order,
                        headerGenerator.getHeadersForSuccessPostMethod(order.getId()),
                        HttpStatus.CREATED);
            }catch (Exception ex){
                ex.printStackTrace();
                return new ResponseEntity<Order>(
                        headerGenerator.getHeadersForError(),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Order>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }

    private Order createOrder(List<Item> cart, String userId) {
        Order order = new Order();
        order.setItems(cart);
        order.setUserId (userId);
        order.setTotal(OrderUtilities.countTotalPrice(cart));
        order.setTotalQuantity (OrderUtilities.countTotalQuantity (cart));
        order.setOrderedDate(LocalDate.now());
        order.setStatus("PAYMENT_EXPECTED");
        return order;
    }
}


