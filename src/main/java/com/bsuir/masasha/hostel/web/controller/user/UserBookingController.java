package com.bsuir.masasha.hostel.web.controller.user;

import com.bsuir.masasha.hostel.core.domain.RoomType;
import com.bsuir.masasha.hostel.core.domain.dto.BasketEntity;
import com.bsuir.masasha.hostel.core.domain.dto.BookingOpportunitiesDTO;
import com.bsuir.masasha.hostel.core.domain.dto.BookingPair;
import com.bsuir.masasha.hostel.core.domain.dto.ResponseStatus;
import com.bsuir.masasha.hostel.core.service.BookingService;
import com.bsuir.masasha.hostel.core.service.HotelEditService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import static com.bsuir.masasha.hostel.web.WebConstants.BASKET_ATR;

@RestController
@RequestMapping("/user/booking")
public class UserBookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private HotelEditService hotelEditService;

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


    @PostMapping(value = "/add/basket", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseStatus addToBasket(@RequestBody final BasketEntity basketEntity, HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        List<BasketEntity> basketEntities = Optional.ofNullable(session.getAttribute(BASKET_ATR))
                .map(be -> (List<BasketEntity>) be)
                .orElse(new ArrayList<>());

        basketEntities.add(basketEntity);
        session.setAttribute(BASKET_ATR, basketEntities);

        return ResponseStatus.SUCCESS;
    }

    @PostMapping("/clear/basket")
    public ResponseStatus clearBasket(HttpServletRequest request) {
        request.getSession(true).setAttribute(BASKET_ATR, null);
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
