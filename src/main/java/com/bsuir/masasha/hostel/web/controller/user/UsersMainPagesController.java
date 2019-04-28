package com.bsuir.masasha.hostel.web.controller.user;

import com.bsuir.masasha.hostel.core.domain.Hotel;
import com.bsuir.masasha.hostel.core.domain.Reservation;
import com.bsuir.masasha.hostel.core.domain.Review;
import com.bsuir.masasha.hostel.core.domain.User;
import com.bsuir.masasha.hostel.core.domain.dto.BasketEntity;
import com.bsuir.masasha.hostel.core.service.BookingService;
import com.bsuir.masasha.hostel.core.service.HotelEditService;
import com.bsuir.masasha.hostel.core.service.LazyInitService;
import com.bsuir.masasha.hostel.core.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.bsuir.masasha.hostel.web.WebConstants.BASKET_ATR;

@Controller
public class UsersMainPagesController {

    private final HotelEditService hotelEditService;
    private final BookingService bookingService;
    private final LazyInitService lazyInitService;
    private final ReviewService reviewService;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public UsersMainPagesController(HotelEditService hotelEditService,
                                    BookingService bookingService,
                                    LazyInitService lazyInitService,
                                    ReviewService reviewService
    ) {
        this.hotelEditService = hotelEditService;
        this.bookingService = bookingService;
        this.lazyInitService = lazyInitService;
        this.reviewService = reviewService;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model,
                       @AuthenticationPrincipal User user) {
        Hotel hotel = hotelEditService.findHotel();
        model.addAttribute("hotel", hotel);
        model.addAttribute("reviews", reviewService.getAllReviews(user));

        boolean alreadyHaveReview = reviewService.isHaveReview(user);
        model.addAttribute("alreadyHaveReview", alreadyHaveReview);
        return "user/main";
    }

    @GetMapping("/booking")
    public String booking(Model model, HttpServletRequest request) {
        Hotel hotel = hotelEditService.findHotel();
        model.addAttribute("hotel", hotel);
        BasketEntity basketEntity = (BasketEntity) request.getSession(true).getAttribute(BASKET_ATR);
        model.addAttribute(BASKET_ATR, basketEntity);
        return "user/book";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal User user, Model model, HttpServletRequest request) {
        Hotel hotel = hotelEditService.findHotel();
        user = lazyInitService.fetchReservations(user.getId());
        Map<Boolean, List<Reservation>> isActiveReservation = user.getReservations().stream()
                .collect(Collectors.partitioningBy(partition ->
                        partition.getEndDate().isAfter(LocalDate.now())
                ));
        List<Reservation> activeReservations = isActiveReservation.get(true);
        List<Reservation> historyReservations = isActiveReservation.get(false);

        model.addAttribute("hotel", hotel);
        model.addAttribute("profile", user);
        model.addAttribute("activeReservations", activeReservations);
        model.addAttribute("historyReservations", historyReservations);
        return "user/profile";
    }

}