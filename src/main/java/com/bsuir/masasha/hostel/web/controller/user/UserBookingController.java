package com.bsuir.masasha.hostel.web.controller.user;

import com.bsuir.masasha.hostel.core.domain.PromoCode;
import com.bsuir.masasha.hostel.core.domain.Reservation;
import com.bsuir.masasha.hostel.core.domain.RoomType;
import com.bsuir.masasha.hostel.core.domain.User;
import com.bsuir.masasha.hostel.core.domain.dto.*;
import com.bsuir.masasha.hostel.core.domain.dto.ResponseStatus;
import com.bsuir.masasha.hostel.core.service.BookingService;
import com.bsuir.masasha.hostel.core.service.HotelEditService;
import com.bsuir.masasha.hostel.core.service.LazyInitService;
import com.bsuir.masasha.hostel.core.service.UserService;
import com.bsuir.masasha.hostel.core.service.exception.BookingFailException;
import com.bsuir.masasha.hostel.core.service.exception.BookingPartFailException;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import static com.bsuir.masasha.hostel.web.WebConstants.BASKET_ATR;

@RestController
@RequestMapping("/user/booking")
public class UserBookingController {

    private final BookingService bookingService;

    private final HotelEditService hotelEditService;


    private final UserService userService;

    @Autowired
    public UserBookingController(BookingService bookingService, HotelEditService hotelEditService, UserService userService) {
        this.bookingService = bookingService;
        this.hotelEditService = hotelEditService;
        this.userService = userService;
    }

    @GetMapping("/options")
    public BookingOpportunitiesDTO findOptions(
            @RequestParam Integer peopleNum,
            @RequestParam Integer maxCost,
            @RequestParam String dates) {

        Map<Long, Pair<Long, BookingPair>> preReservations = bookingService.pickOptions(peopleNum, maxCost, dates);
        BookingOpportunitiesDTO answer = new BookingOpportunitiesDTO();

        if (preReservations.isEmpty()) {
            answer.setStatus(ResponseStatus.WARNING);
            answer.setMessage("К сожелению на данный момент нет подходящих вариантов, может вам подойдут эти номера?");
        } else {
            answer.setStatus(ResponseStatus.SUCCESS);
            answer.setOptions(preReservations);
        }
        Map<Long, Pair<Long, BookingPair>> sameDateAlternatives = bookingService.sameDateAlternatives(peopleNum, maxCost, dates);
        answer.setAlternatives(sameDateAlternatives);

        return answer;
    }

    @GetMapping("/alternatives/sameDate")
    public BookingOpportunitiesDTO sameDateAlternatives(
            @RequestParam Integer peopleNum,
            @RequestParam Integer maxCost,
            @RequestParam String dates) {

        BookingOpportunitiesDTO answer = new BookingOpportunitiesDTO();

        Map<Long, Pair<Long, BookingPair>> sameDateAlternatives = bookingService.sameDateAlternatives(peopleNum, maxCost, dates);
        if (sameDateAlternatives.isEmpty()) {
            answer.setStatus(ResponseStatus.WARNING);
            answer.setMessage("Нет подходящих вариантов");
        } else {
            answer.setStatus(ResponseStatus.SUCCESS);
            answer.setAlternatives(sameDateAlternatives);
        }

        return answer;
    }


    @GetMapping("/roomTypes")
    public Map<Long, RoomType> getRoomTypes() {
        Map<Long, RoomType> roomTypes = new HashMap<>();
        hotelEditService.getAllRoomTypes().forEach(roomType -> roomTypes.put(roomType.getId(), roomType));
        return roomTypes;
    }

    @PostMapping("/delete/{id}")
    public SimpleResponse deleteReservation(@PathVariable Long id) {
        userService.deleteReservation(id);
        return new SimpleResponse(ResponseStatus.SUCCESS, "");
    }


    @PostMapping(value = "/add/basket", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseStatus addToBasket(@RequestBody final BasketEntityDTO basketEntityDTO, HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        BasketEntity basketEntity = Optional.ofNullable(session.getAttribute(BASKET_ATR))
                .map(be -> (BasketEntity) be)
                .orElse(new BasketEntity());

        RoomType roomType = hotelEditService.getRoomTypeById(basketEntityDTO.getRoomTypeId());
        basketEntityDTO.setRoomType(roomType);

        basketEntity.addEntity(basketEntityDTO);
        session.setAttribute(BASKET_ATR, basketEntity);

        return ResponseStatus.SUCCESS;
    }

    @PostMapping(value = "/add/promo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseStatus addToBasket(@RequestBody final String promo, HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        BasketEntity basketEntity = Optional.ofNullable(session.getAttribute(BASKET_ATR))
                .map(be -> (BasketEntity) be)
                .orElse(new BasketEntity());

        PromoCode confirmedPromo = hotelEditService.checkPromoCode(promo);
        if (confirmedPromo != null) {
            basketEntity.setPromocode(confirmedPromo);
            session.setAttribute(BASKET_ATR, basketEntity);
            return ResponseStatus.SUCCESS;
        }
        return ResponseStatus.ERROR;
    }

    @PostMapping("/clear/basket")
    public ResponseStatus clearBasket(HttpServletRequest request) {
        request.getSession(true).setAttribute(BASKET_ATR, null);
        return ResponseStatus.SUCCESS;
    }

    @PostMapping("/book")
    public ResponseStatus book(@AuthenticationPrincipal User user, HttpServletRequest request) {
        BasketEntity basketEntity = (BasketEntity) request.getSession(true).getAttribute(BASKET_ATR);
        try {
            bookingService.book(basketEntity, user);
            request.getSession().removeAttribute(BASKET_ATR);
        } catch (BookingFailException exc) {
            return ResponseStatus.ERROR;
        } catch (BookingPartFailException exc) {
            return ResponseStatus.WARNING;
        }

        return ResponseStatus.SUCCESS;
    }


    @GetMapping("/alternatives/sameRoomType")
    public BookingOpportunitiesDTO sameRoomTypeAlternatives(
            @RequestParam Integer peopleNum,
            @RequestParam Integer maxCost,
            @RequestParam String dates) {

//        BookingOpportunitiesDTO answer = new BookingOpportunitiesDTO();
//
//        Map<Reservation, RoomType> sameRoomTypeAlternatives = bookingService.sameRoomTypeAlternatives(peopleNum, maxCost, dates);
//        if (sameRoomTypeAlternatives.isEmpty()) {
//            answer.setStatus(ResponseStatus.WARNING);
//            answer.setMessage("Нет подходящих вариантов");
//        } else  {
//            answer.setStatus(ResponseStatus.SUCCESS);
//            answer.setAlternatives(sameRoomTypeAlternatives);
//        }

        return new BookingOpportunitiesDTO();
    }

    @GetMapping("/alternatives/other")
    public BookingOpportunitiesDTO otherAlternatives(
            @RequestParam Integer peopleNum,
            @RequestParam Integer maxCost,
            @RequestParam String dates) {

//        BookingOpportunitiesDTO answer = new BookingOpportunitiesDTO();
//
//        Map<Reservation, RoomType> otherAlternatives = bookingService.otherAlternatives(peopleNum, maxCost, dates);
//        if (otherAlternatives.isEmpty()) {
//            answer.setStatus(ResponseStatus.WARNING);
//            answer.setMessage("Нет подходящих вариантов");
//        } else  {
//            answer.setStatus(ResponseStatus.SUCCESS);
//            answer.setAlternatives(otherAlternatives);
//        }
//
        return new BookingOpportunitiesDTO();
    }
}
