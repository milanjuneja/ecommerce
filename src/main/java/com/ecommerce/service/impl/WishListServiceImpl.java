package com.ecommerce.service.impl;

import com.ecommerce.modal.Product;
import com.ecommerce.modal.User;
import com.ecommerce.modal.WishList;
import com.ecommerce.repository.WishListRepository;
import com.ecommerce.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListServiceImpl implements WishListService {

    @Autowired
    private WishListRepository wishListRepository;
    @Override
    public WishList createWishList(User user) {
        WishList wishList = new WishList();
        wishList.setUser(user);
        return wishListRepository.save(wishList);
    }

    @Override
    public WishList getWishListByUser(User user) {
        WishList wishList = wishListRepository.findByUserId(user.getId());
        if(wishList == null)
            wishList = createWishList(user);
        return wishList;
    }

    @Override
    public WishList addProductToWishList(User user, Product product) {
        WishList wishList = getWishListByUser(user);

        if(wishList.getProducts().contains(product)){
            wishList.getProducts().remove(product);
        }else wishList.getProducts().add(product);

        return wishListRepository.save(wishList);
    }
}
