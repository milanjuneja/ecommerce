package com.ecommerce.controller;

import com.ecommerce.modal.Product;
import com.ecommerce.modal.Review;
import com.ecommerce.modal.User;
import com.ecommerce.request.CreateReviewRequest;
import com.ecommerce.response.ApiResponse;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.ReviewService;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getReviewsByProductId(
            @PathVariable Long productId,
            @RequestHeader("Authorization") String jwt
    ) {
        return new ResponseEntity<>(reviewService.getReviewByProductId(productId), HttpStatus.OK);
    }

    @PostMapping("/product/{productId}")
    public ResponseEntity<Review> createReview(
            @PathVariable Long productId,
            @RequestBody CreateReviewRequest req,
            @RequestHeader("Authorization") String jwt
            ) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Product product = productService.findProductById(productId);

        return new ResponseEntity<>(reviewService.createReview(
                req, user, product
        ), HttpStatus.OK);

    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(
            @PathVariable Long reviewId,
            @RequestBody CreateReviewRequest req,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        return new ResponseEntity<>(reviewService.updateReview(
                reviewId,
                req.getReviewText(),
                req.getReviewRating(),
                user.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReview(
            @PathVariable Long reviewId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        reviewService.deleteReview(reviewId, user.getId());

        ApiResponse res = new ApiResponse();
        res.setMessage("Review Deleted successfully");
        return ResponseEntity.ok(res);
    }


}
