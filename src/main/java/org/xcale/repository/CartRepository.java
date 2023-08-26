package org.xcale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xcale.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
