package com.ecommerce.controller;

import com.ecommerce.modal.Cart;
import com.ecommerce.modal.Coupon;
import com.ecommerce.modal.User;
import com.ecommerce.service.CartService;
import com.ecommerce.service.CouponService;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupons")
public class AdminCouponController {
    @Autowired
    private CouponService couponService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    @PostMapping("/apply")
    public ResponseEntity<Cart> applyCoupon(
            @RequestParam String apply,
            @RequestParam String code,
            @RequestParam double orderValue,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Cart cart;
        if(apply.equals("true"))
            cart = couponService.applyCoupon(code, orderValue, user);
        else cart = couponService.removeCoupon(code, user);

        return ResponseEntity.ok(cart);
    }

    @PostMapping("/create")
    public ResponseEntity<Coupon> createCoupon(
            @RequestBody Coupon coupon
    ){
        return new ResponseEntity<>(couponService.createCoupon(coupon), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCoupon(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {
        couponService.deleteCoupon(id);
        return ResponseEntity.ok("Coupon deleted successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Coupon>> getAllCoupons(@RequestHeader("Authorization") String jwt){
        return new ResponseEntity<>(couponService.findAllCoupons(), HttpStatus.OK);
    }


}
