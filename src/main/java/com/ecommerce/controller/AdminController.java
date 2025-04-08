package com.ecommerce.controller;

import com.ecommerce.domain.AccountStatus;
import com.ecommerce.modal.Seller;
import com.ecommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class AdminController {
    @Autowired
    private SellerService sellerService;

    @PatchMapping("/{id}/status/{status}")
    public ResponseEntity<Seller> updateSellerStatus(
            @PathVariable Long id,
            @PathVariable AccountStatus status,
            @RequestHeader("Authorization") String jwt
            ) throws Exception {
        return new ResponseEntity<>(sellerService.updateSellerAccountStatus(id, status), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Seller>> getAllSeller(@RequestParam(required = false) AccountStatus status,
                                                     @RequestHeader("Authorization") String jwt){
        return new ResponseEntity<>(sellerService.getAllSellers(status), HttpStatus.OK);
    }
}
