package com.bsuir.masasha.hostel.web.controller.user;

import com.bsuir.masasha.hostel.core.domain.User;
import com.bsuir.masasha.hostel.core.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.bsuir.masasha.hostel.web.WebConstants.REDIRECT;
import static com.bsuir.masasha.hostel.web.WebConstants.USER_MAIN_PAGE_MAPPING;

@Controller
@RequestMapping("/user/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add")
    public String addReview(
            @RequestParam String title,
            @RequestParam String type,
            @RequestParam String text,
            @AuthenticationPrincipal User user
    ) {
        reviewService.addReview(title, type, text, user);

        return REDIRECT + USER_MAIN_PAGE_MAPPING;
    }

    @PostMapping("/like")
    public String like(@RequestParam Long id, @AuthenticationPrincipal User user) {
        reviewService.like(id, user);
        return REDIRECT + USER_MAIN_PAGE_MAPPING;
    }
}
