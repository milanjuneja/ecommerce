package com.ecommerce.repository;

import com.ecommerce.modal.Cart;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUserId(Long userId);
}
