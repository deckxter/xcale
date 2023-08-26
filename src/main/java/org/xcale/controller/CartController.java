package org.xcale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xcale.entity.Cart;
import org.xcale.entity.Product;
import org.xcale.service.CartService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        try {
            Cart cartSaved = cartService.save(cart);
            return new ResponseEntity<>(cartSaved, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cart> findByIdCart(@PathVariable("id")Long idCart) {
        Optional<Cart> cart = cartService.findByIdCart(idCart);

        return cart.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable("id")Long idCart, @RequestBody Cart cart) {
        try {
            Cart cartUpdated = cartService.update(idCart, cart);
            return new ResponseEntity<>(cartUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCart(@PathVariable("id")Long idCart) {
        cartService.deleteCart(idCart);
    }
}
