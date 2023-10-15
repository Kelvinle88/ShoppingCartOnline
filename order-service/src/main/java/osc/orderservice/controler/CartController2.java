package osc.orderservice.controler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osc.orderservice.entity.Cart;
import osc.orderservice.entity.CartItem;
import osc.orderservice.service.CartService2;

import java.util.ArrayList;

@RestController
@RequestMapping("/carts")
public class CartController2 {

    private final CartService2 cartService2;

    public CartController2 (CartService2 cartService2) {
        this.cartService2 = cartService2;
    }

    @GetMapping("/{userId}")
    public ResponseEntity <Cart> getCart (@PathVariable String userId) {
        Cart cart = cartService2.getCart (userId);
        if (cart != null) {
            return ResponseEntity.ok (cart);
        } else {
            return ResponseEntity.notFound ().build ();
        }
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity <Cart> addItemToCart (
            @PathVariable String userId,
            @RequestBody CartItem cartItem) {
        Cart cart = cartService2.getCart (userId);
        if (cart == null) {
            cart = new Cart (userId,new ArrayList <> ());
        }
        cart.addItem (cartItem);
        cartService2.updateCart (userId,cart);
        return ResponseEntity.ok (cart);
    }

    @PutMapping
    public void updateCart (String userId,@RequestBody Cart cart) {
        cartService2.updateCart (userId,cart);
    }

    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity <Cart> removeItemFromCart (
            @PathVariable String userId,
            @PathVariable Long productId) {
        Cart cart = cartService2.getCart (userId);
        if (cart != null) {
            cart.removeItem (productId);
            cartService2.updateCart (userId,cart);
            return ResponseEntity.ok (cart);
        } else {
            return ResponseEntity.notFound ().build ();
        }
    }
}
