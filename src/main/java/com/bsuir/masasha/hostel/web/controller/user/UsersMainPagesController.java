package com.bsuir.masasha.hostel.web.controller.user;

import com.bsuir.masasha.hostel.core.domain.Hotel;
import com.bsuir.masasha.hostel.core.domain.Reservation;
import com.bsuir.masasha.hostel.core.domain.User;
import com.bsuir.masasha.hostel.core.domain.dto.BasketEntity;
import com.bsuir.masasha.hostel.core.service.BookingService;
import com.bsuir.masasha.hostel.core.service.HotelEditService;
import com.bsuir.masasha.hostel.core.service.LazyInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.bsuir.masasha.hostel.web.WebConstants.BASKET_ATR;

@Controller
public class UsersMainPagesController {

    private final HotelEditService hotelEditService;
    private final BookingService bookingService;
    private final LazyInitService lazyInitService;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public UsersMainPagesController(HotelEditService hotelEditService, BookingService bookingService, LazyInitService lazyInitService) {
        this.hotelEditService = hotelEditService;
        this.bookingService = bookingService;
        this.lazyInitService = lazyInitService;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model) {
        Hotel hotel = hotelEditService.findHotel();
        model.addAttribute("hotel", hotel);
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


//
//    @GetMapping("/main")
//    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
//        Iterable<Message> messages;
//
//        if (filter == null || filter.isEmpty()) {
//            messages = messageRepository.findAll();
//        } else {
//            messages = messageRepository.findByTag(filter);
//        }
//
//        model.addAttribute("messages", messages);
//        model.addAttribute("filter", filter);
//
//        return "user/main";
//    }
//
//    @PostMapping("/main")
//    public String add(
//            @AuthenticationPrincipal User user,
//            @RequestParam String text,
//            @RequestParam String tag, Map<String, Object> model,
//            @RequestParam("file") MultipartFile file
//    ) throws IOException {
//        Message message = new Message(text, tag, user);
//
//        if (file != null) {
//            File uploadDir = new File(uploadPath);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//
//            String uuidFile = UUID.randomUUID().toString();
//            String resultFilename = uuidFile + "." + file.getOriginalFilename();
//
//            file.transferTo(new File(uploadPath + "/" + resultFilename));
//
//            message.setFilename(resultFilename);
//        }
//
//        messageRepository.save(message);
//
//        model.put("filter", "");
//
//        Iterable<Message> messages = messageRepository.findAll();
//        model.put("messages", messages);
//        return "main";
//    }

}