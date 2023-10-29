package osc.controler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osc.headergenerator.HeaderGenerator;
import osc.service.CartService;

import java.util.List;
//import javax.servlet.http.HttpServletRequest;
@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    private HeaderGenerator headerGenerator;

    @GetMapping ()
    public ResponseEntity<List<Object>> getCart(@RequestHeader(value = "Cookie") String cartId){
        List<Object> cart = cartService.getCart(cartId);
        if(!cart.isEmpty()) {
            return new ResponseEntity<List<Object>>(
                    cart,
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<List<Object>>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }

    @PostMapping(params = {"productId", "quantity"})
    public ResponseEntity<List<Object>> addItemToCart(
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") Integer quantity,
            @RequestHeader(value = "Cookie") String cartId) {
        List<Object> cart = cartService.getCart(cartId);
        if(cart != null) {
            if(cart.isEmpty()){
                cartService.addItemToCart(cartId, productId, quantity);
            }else{
                if(cartService.checkIfItemIsExist(cartId, productId)){
                    cartService.changeItemQuantity(cartId, productId, quantity);
                }else {
                    cartService.addItemToCart(cartId, productId, quantity);
                }
            }
            return new ResponseEntity<List<Object>>(
                    cart,
                    //headerGenerator.getHeadersForSuccessPostMethod (Long.parseLong (cartId)),
                    HttpStatus.CREATED);
        }
        return new ResponseEntity<List<Object>>(
                //headerGenerator.getHeadersForError(),
                HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(params = "productId")
    public ResponseEntity<Void> removeItemFromCart(
            @RequestParam("productId") Long productId,
            @RequestHeader(value = "Cookie") String cartId){
        List<Object> cart = cartService.getCart(cartId);
        if(!cart.isEmpty ()) {
            cartService.deleteItemFromCart(cartId, productId);
            return new ResponseEntity<Void>(
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<Void>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }
}
