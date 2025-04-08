package com.ecommerce.service;

import com.ecommerce.modal.Product;
import com.ecommerce.modal.Review;
import com.ecommerce.modal.User;
import com.ecommerce.request.CreateReviewRequest;

import java.util.List;

public interface ReviewService {

    Review createReview(CreateReviewRequest req, User user, Product product);

    List<Review> getReviewByProductId(Long productId);

    Review updateReview(Long reviewId, String reviewText, double rating, Long userId) throws Exception;

    void deleteReview(Long reviewId, Long userId) throws Exception;

    Review findReviewById(Long reviewId) throws Exception;


}
