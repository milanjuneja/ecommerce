package com.ecommerce.service.impl;

import com.ecommerce.modal.Product;
import com.ecommerce.modal.Review;
import com.ecommerce.modal.User;
import com.ecommerce.repository.ReviewRepository;
import com.ecommerce.request.CreateReviewRequest;
import com.ecommerce.service.ReviewService;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Override
    public Review createReview(CreateReviewRequest req, User user, Product product) {
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReviewText(req.getReviewText());
        review.setRating(req.getReviewRating());
        review.setProductImages(req.getProductImages());

        product.getReviews().add(review);

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    @Override
    public Review updateReview(Long reviewId, String reviewText, double rating, Long userId) throws Exception {
        Review review = findReviewById(reviewId);
        if(review.getUser().getId().equals(userId)){
            review.setReviewText(reviewText);
            review.setRating(rating);
            return reviewRepository.save(review);
        }
        throw new Exception("You can't update this review");
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) throws Exception {
        Review review = findReviewById(reviewId);
        if(!review.getUser().getId().equals(userId))
            throw new Exception("You can't delete this review");
        reviewRepository.delete(review);
    }

    @Override
    public Review findReviewById(Long reviewId) throws Exception {
        return reviewRepository.findById(reviewId).orElseThrow(() ->
                new Exception("Review not found"));
    }
}
