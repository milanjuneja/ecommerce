package com.ecommerce.controller;

import com.ecommerce.exception.ProductException;
import com.ecommerce.modal.Product;
import com.ecommerce.modal.User;
import com.ecommerce.modal.WishList;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;
import com.ecommerce.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

//    @PostMapping("/create")
//    public ResponseEntity<WishList> createWishList(@RequestBody User user){
//        return new ResponseEntity<>(wishListService.createWishList(user), HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<WishList> getWishListByUserId(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        return new ResponseEntity<>(wishListService.getWishListByUser(user), HttpStatus.OK);
    }

    @PostMapping("/add-product/{productId}")
    public ResponseEntity<WishList> addProductToWishList(
            @PathVariable Long productId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Product product = productService.findProductById(productId);
        User user = userService.findUserByJwtToken(jwt);
        WishList updatedWishList = wishListService.addProductToWishList(user, product);

        return ResponseEntity.ok(updatedWishList);
    }

}
