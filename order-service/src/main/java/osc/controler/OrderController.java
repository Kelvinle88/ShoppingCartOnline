package osc.controler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import osc.client.PaymentFeignClient;
import osc.client.ProductFeignClient;
import osc.constant.OrderStatus;
import osc.consumer.OrderConsumer;
import osc.dto.OrderDto;
import osc.entity.Item;
import osc.entity.Order;
import osc.headergenerator.HeaderGenerator;
import osc.mapper.UserMapper;
import osc.publisher.OrderPublisher;
import osc.security.AuthHelper;
import osc.service.CartService;
import osc.service.OrderService;
import osc.utilities.OrderUtilities;

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
    @Autowired
    private PaymentFeignClient paymentFeignClient;

    private UserMapper userMapper;

    private AuthHelper authHelper;

    @Autowired
    private OrderConsumer orderConsumer;
    @Autowired
    private OrderPublisher orderPublisher;

    public OrderController (AuthHelper authHelper) {
        this.authHelper = authHelper;
    }

    @PostMapping()
    public ResponseEntity <Order> checkOut(
            @RequestHeader(value = "Cookie") String cartId)
            //HttpServletRequest request)
    {
        String userId = authHelper.getUserId ();
        List <Item> cart = cartService.getAllItemsFromCart(cartId);
        if(cart != null && userId != null) {
            Order order = this.createOrder(cart, userId);
            try{
                orderService.saveOrder(order);
                cartService.deleteCart(cartId);
                orderPublisher.placeOrderMessage (new OrderDto (order.getId (),order.getUserId (),order.getTotal (),OrderStatus.PROCESSING));
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
        order.setStatus(OrderStatus.PROCESSING);
        return order;
    }
}


