package com.bsuir.masasha.hostel.core.service;

import com.bsuir.masasha.hostel.core.domain.Review;
import com.bsuir.masasha.hostel.core.domain.User;

import java.util.List;

public interface ReviewService {

    void addReview(String title, String type, String text, User user);

    List<Review> getAllReviews();
}
