package com.bsuir.masasha.hostel.web.controller.user;

import com.bsuir.masasha.hostel.core.domain.RoomType;
import com.bsuir.masasha.hostel.core.domain.dto.BookingOpportunitiesDTO;
import com.bsuir.masasha.hostel.core.domain.dto.ResponseStatus;
import com.bsuir.masasha.hostel.core.domain.dto.BookingPair;
import com.bsuir.masasha.hostel.core.service.BookingService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user/booking")
public class UserBookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/options")
    public BookingOpportunitiesDTO findOptions(
            @RequestParam Integer peopleNum,
            @RequestParam Integer maxCost,
            @RequestParam String dates) {

        Map<RoomType, Pair<Long, BookingPair>> preReservations = bookingService.pickOptions(peopleNum, maxCost, dates);
        BookingOpportunitiesDTO answer = new BookingOpportunitiesDTO();

        if (preReservations.isEmpty()) {
            answer.setStatus(ResponseStatus.WARING);
            answer.setMessage("К сожелению на данный момент нет подходящих вариантов, может вам подойдут эти номера?");
        } else {
            answer.setStatus(ResponseStatus.SUCCESS);
            answer.setReservations(preReservations);
        }
        Map<RoomType, Pair<Long, BookingPair>> sameDateAlternatives = bookingService.sameDateAlternatives(peopleNum, maxCost, dates);
        answer.setAlternatives(sameDateAlternatives);

        return answer;
    }

    @GetMapping("/alternatives/sameDate")
    public BookingOpportunitiesDTO sameDateAlternatives(
            @RequestParam Integer peopleNum,
            @RequestParam Integer maxCost,
            @RequestParam String dates) {

        BookingOpportunitiesDTO answer = new BookingOpportunitiesDTO();

        Map<RoomType, Pair<Long, BookingPair>> sameDateAlternatives = bookingService.sameDateAlternatives(peopleNum, maxCost, dates);
        if (sameDateAlternatives.isEmpty()) {
            answer.setStatus(ResponseStatus.WARING);
            answer.setMessage("Нет подходящих вариантов");
        } else  {
            answer.setStatus(ResponseStatus.SUCCESS);
            answer.setAlternatives(sameDateAlternatives);
        }

        return answer;
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
//            answer.setStatus(ResponseStatus.WARING);
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
//            answer.setStatus(ResponseStatus.WARING);
//            answer.setMessage("Нет подходящих вариантов");
//        } else  {
//            answer.setStatus(ResponseStatus.SUCCESS);
//            answer.setAlternatives(otherAlternatives);
//        }
//
        return new BookingOpportunitiesDTO();
    }
}
