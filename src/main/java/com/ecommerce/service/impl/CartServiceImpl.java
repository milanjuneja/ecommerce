package com.ecommerce.service.impl;

import com.ecommerce.modal.Cart;
import com.ecommerce.modal.CartItems;
import com.ecommerce.modal.Product;
import com.ecommerce.modal.User;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItems addCartItem(User user, Product product, String size, int quantity) {
        Cart cart = findUserCart(user);
        CartItems isPresent = cartItemRepository.findByCartAndProductAndSize(cart, product, size);
        if(isPresent == null){
            CartItems cartItems = new CartItems();
            cartItems.setProduct(product);
            cartItems.setQuantity(quantity);
            cartItems.setUserId(user.getId());
            cartItems.setSize(size);
            cartItems.setMrpPrice(quantity * product.getMrpPrice());
            cart.getCartItems().add(cartItems);
            cartItems.setCart(cart);


            int totalPrice = quantity * product.getSellingPrice();
            cartItems.setSellingPrice(totalPrice);
            return cartItemRepository.save(cartItems);
        }
        return isPresent;
    }

    @Override
    public Cart findUserCart(User user) {
        Cart cart = cartRepository.findByUserId(user.getId());
        int totalPrice = 0;
        int totalDiscountedPrice = 0;
        int totalItem = 0;

        for (CartItems cartItem: cart.getCartItems()) {
            totalPrice += cartItem.getMrpPrice();
            totalDiscountedPrice += cartItem.getSellingPrice();
            totalItem += cartItem.getQuantity();
        }
        cart.setTotalMrpPrice(totalPrice);
        cart.setTotalItems(totalItem);
        cart.setTotalSellingPrice(totalDiscountedPrice);
        cart.setDiscount(calculateDiscountPercentage(totalPrice, totalDiscountedPrice));
        return cart;
    }
    private int calculateDiscountPercentage(double mrpPrice, double sellingPrice){
        if(mrpPrice <= 0)
            return 0;
        double discount = mrpPrice - sellingPrice;
        double discountPercentage = (discount/mrpPrice) * 100;
        return (int) discountPercentage;
    }
}
