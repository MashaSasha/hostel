package com.bsuir.masasha.hostel.core.service;

import com.bsuir.masasha.hostel.core.domain.Review;
import com.bsuir.masasha.hostel.core.domain.User;
import com.bsuir.masasha.hostel.core.domain.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    void addReview(String title, String type, String text, User user);

    List<ReviewDTO> getAllReviews(User user);

    boolean isHaveReview(User user);

    void like(Long id, User user);
}
