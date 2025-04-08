package com.ecommerce.controller;

import com.ecommerce.modal.Home;
import com.ecommerce.modal.HomeCategory;
import com.ecommerce.service.HomeCategoryService;
import com.ecommerce.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class HomeCategoryController {

    @Autowired
    private HomeCategoryService homeCategoryService;
    @Autowired
    private HomeService homeService;

    @PostMapping("/home/categories")
    @PreAuthorize("hasRole ('Admin')")
    public ResponseEntity<Home> createHomeCategories(
            @RequestBody List<HomeCategory> homeCategories
            ){
        List<HomeCategory> categories = homeCategoryService.createHomeCategories(homeCategories);
        return new ResponseEntity<>(homeService.createHomePageData(categories), HttpStatus.OK);
    }

    @GetMapping("/home-category")
    @PreAuthorize("hasRole ('Admin')")
    public ResponseEntity<List<HomeCategory>> getHomeCategory(){
        return new ResponseEntity<>(homeCategoryService.getAllHomeCategories(), HttpStatus.OK);
    }

    @PatchMapping("/home-category/{id}")
    @PreAuthorize("hasRole ('Admin')")
    public ResponseEntity<HomeCategory> updateHomeCategory(
            @PathVariable Long id,
            @RequestBody HomeCategory homeCategory
    ) throws Exception {

        return new ResponseEntity<>(homeCategoryService.updateHomeCategory(homeCategory, id), HttpStatus.OK);

    }

}
