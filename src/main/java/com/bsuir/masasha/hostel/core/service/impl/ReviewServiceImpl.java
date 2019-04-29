package com.bsuir.masasha.hostel.core.service.impl;

import com.bsuir.masasha.hostel.core.domain.Review;
import com.bsuir.masasha.hostel.core.domain.User;
import com.bsuir.masasha.hostel.core.domain.dto.ReviewDTO;
import com.bsuir.masasha.hostel.core.repo.ReviewRepository;
import com.bsuir.masasha.hostel.core.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void addReview(String title, String type, String text, User user) {
        Review review = new Review(title, type, text, user);
        reviewRepository.save(review);
    }

    @Override
    public List<ReviewDTO> getAllReviews(User user) {
        return reviewRepository.findAllDTO(user);
    }

    @Override
    public boolean isHaveReview(User user) {
        return reviewRepository.getReviewByUser(user) != null;
    }

    @Override
    public void like(Long id, User currentUser) {
        Review review = reviewRepository.findById(id).get();
        Set<User> likes = review.getLikes();
        if (likes.contains(currentUser)) {
            likes.remove(currentUser);
        } else {
            likes.add(currentUser);
        }
        reviewRepository.save(review);
    }
}
