package com.ecommerce.controller;

import com.ecommerce.domain.OrderStatus;
import com.ecommerce.modal.Order;
import com.ecommerce.modal.Seller;
import com.ecommerce.service.OrderService;
import com.ecommerce.service.SellerService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seller/orders")
public class SellerOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private SellerService sellerService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(@RequestHeader("Authorization") String jwt) throws Exception {
        Seller seller = sellerService.getSellerProfileFromJwt(jwt);
        return new ResponseEntity<>(orderService.sellersOrder(seller.getId()), HttpStatus.OK);
    }

    @PatchMapping("/{orderId}/status/{orderStatus}")
    public ResponseEntity<Order> updateOrder(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long orderId,
            @PathVariable OrderStatus orderStatus
            ) throws Exception {
        return new ResponseEntity<>(orderService.updateOrderStatus(orderId, orderStatus), HttpStatus.OK);
    }


}
