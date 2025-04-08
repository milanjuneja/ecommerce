package com.ecommerce.service;

import com.ecommerce.modal.CartItems;

public interface CartItemService {

    CartItems updateCartItem(Long userId, Long cartItemId ,CartItems cartItems) throws Exception;
    void removeCartItem(Long userId, Long cartItemId) throws Exception;

    CartItems findCartItemById(Long id) throws Exception;
}
