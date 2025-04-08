package com.ecommerce.controller;

import com.ecommerce.modal.Deal;
import com.ecommerce.response.ApiResponse;
import com.ecommerce.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/deals")
public class DealController {

    @Autowired
    private DealService dealService;

    @GetMapping
    public ResponseEntity<List<Deal>> getAllDeals(@RequestHeader("Authorization") String jwt){
        return new ResponseEntity<>(dealService.getDeals(), HttpStatus.ACCEPTED);
    }
    @PostMapping
    public ResponseEntity<Deal> createDeal(@RequestBody Deal deal){
        return new ResponseEntity<>(dealService.createDeal(deal), HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Deal> updateDeal(
            @PathVariable Long id,
            @RequestBody Deal deal
    ) throws Exception {
        return new ResponseEntity<>(dealService.updateDeal(deal, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteDeal(@PathVariable Long id) throws Exception {
        dealService.deleteDeal(id);
        return new ResponseEntity<>(new ApiResponse("Deal Deleted"), HttpStatus.ACCEPTED);
    }

}
