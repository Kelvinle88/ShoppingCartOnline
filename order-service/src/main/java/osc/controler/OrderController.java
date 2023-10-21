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
import osc.dto.OrderDto;
import osc.entity.Item;
import osc.entity.Order;
import osc.headergenerator.HeaderGenerator;
import osc.mapper.OrderMapper;
import osc.mapper.UserMapper;
import osc.security.AuthHelper;
import osc.service.CartService;
import osc.service.OrderService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
    private  OrderMapper orderMapper;

    public OrderController (AuthHelper authHelper) {
        this.authHelper = authHelper;
    }

    @PostMapping()
    public ResponseEntity <OrderDto> checkOut(
            @RequestHeader(value = "Cookie") String cartId)
            //HttpServletRequest request)
    {
        String userId = authHelper.getUserId ();
        List <Item> cart = cartService.getAllItemsFromCart(cartId);
        if(cart != null && userId != null) {
            Order order = orderService.createOrder(cart, userId);
            try{
                orderService.saveOrder(order);
                cartService.deleteCart(cartId);
                //orderPublisher.placeOrderMessage (new OrderDto (order.getId (),order.getUserId (),order.getTotal (),OrderStatus.PROCESSING));
                OrderDto orderDto = orderMapper.toDto (order);
                CompletableFuture <OrderDto> orderFuture =  orderService.requestPaymentOrder (orderDto);
                return orderFuture.thenApplyAsync(dto -> new ResponseEntity<>(
                        dto,
                        //headerGenerator.getHeadersForSuccessPostMethod(dto.getId()),
                        HttpStatus.CREATED
                )).join();

//                return new ResponseEntity<OrderDto>(
//                        orderDto,
//                        headerGenerator.getHeadersForSuccessPostMethod(orderDto.getId()),
//                        HttpStatus.CREATED);
            }catch (Exception ex){
                ex.printStackTrace();
                return new ResponseEntity<OrderDto>(
                        headerGenerator.getHeadersForError(),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<OrderDto>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }


}


