package com.ecommerce.service;

import com.ecommerce.modal.Cart;
import com.ecommerce.modal.CartItems;
import com.ecommerce.modal.Product;
import com.ecommerce.modal.User;

public interface CartService {

    CartItems addCartItem(
            User user,
            Product product,
            String size,
            int quantity
    );

    Cart findUserCart(User user);

}
