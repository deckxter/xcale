package org.xcale.service;

import org.springframework.stereotype.Service;
import org.xcale.entity.Cart;
import org.xcale.repository.CartRepository;
import org.xcale.repository.ProductRepository;

import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public Optional<Cart> findByIdCart(Long idCart) {
        return cartRepository.findById(idCart);
    }

    public Cart update(Long id, Cart cart) {
        Cart cartDb = cartRepository.findById(id).orElseThrow();
        cartDb.setProducts(cart.getProducts());
        return cartRepository.save(cartDb);
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
