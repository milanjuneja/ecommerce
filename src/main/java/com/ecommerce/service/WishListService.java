package com.ecommerce.service;

import com.ecommerce.modal.Product;
import com.ecommerce.modal.User;
import com.ecommerce.modal.WishList;

public interface WishListService {

    WishList createWishList(User user);

    WishList getWishListByUser(User user);

    WishList addProductToWishList(User user, Product product);

}
