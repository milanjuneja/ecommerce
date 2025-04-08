package com.ecommerce.repository;

import com.ecommerce.modal.Cart;
import com.ecommerce.modal.CartItems;
import com.ecommerce.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems, Long> {
    CartItems findByCartAndProductAndSize(Cart cart, Product product, String size);
}
