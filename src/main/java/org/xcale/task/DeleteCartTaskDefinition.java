package org.xcale.task;

import org.springframework.stereotype.Service;
import org.xcale.repository.CartRepository;

@Service
public class DeleteCartTaskDefinition implements Runnable {
    private final CartRepository cartRepository;

    private Long idCart;

    public DeleteCartTaskDefinition(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void run() {
        System.out.println(System.currentTimeMillis());
        if(cartRepository.findById(idCart).isPresent()) {
            System.out.println("Running delete cart task");
            cartRepository.deleteById(idCart);
            System.out.println("Task finished");
        }
    }

    public Long getIdCart() {
        return idCart;
    }

    public void setIdCart(Long idCart) {
        this.idCart = idCart;
    }

}
