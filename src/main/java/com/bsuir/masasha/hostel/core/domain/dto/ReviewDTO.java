package com.bsuir.masasha.hostel.core.domain.dto;

import com.bsuir.masasha.hostel.core.domain.Review;
import com.bsuir.masasha.hostel.core.domain.User;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ReviewDTO {

    private Long id;
    private String title;
    private String text;
    private String type;
    private LocalDate date;

    private User author;

    private Long likes;
    private boolean meLiked;

    public ReviewDTO(Review review, Long likes, boolean meLiked) {
        this.author = review.getAuthor();
        this.id = review.getId();
        this.title = review.getTitle();
        this.text = review.getText();
        this.type = review.getType();
        this.date = review.getDate();
        this.likes = likes;
        this.meLiked = meLiked;
    }
}
