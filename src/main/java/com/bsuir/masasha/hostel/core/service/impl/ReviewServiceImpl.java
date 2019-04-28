package com.bsuir.masasha.hostel.core.service.impl;

import com.bsuir.masasha.hostel.core.domain.Review;
import com.bsuir.masasha.hostel.core.domain.User;
import com.bsuir.masasha.hostel.core.domain.dto.ReviewDTO;
import com.bsuir.masasha.hostel.core.repo.ReviewRepository;
import com.bsuir.masasha.hostel.core.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void addReview(String title, String type, String text, User user) {
        //TODO check if exists
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
    public void like(Long id, User user) {
        // если мой лайк стоит то снять
        // иначе поставить
    }
}
