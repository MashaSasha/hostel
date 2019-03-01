package com.bsuir.masasha.hostel.core.service.impl;

import com.bsuir.masasha.hostel.core.domain.Reservation;
import com.bsuir.masasha.hostel.core.domain.RoomType;
import com.bsuir.masasha.hostel.core.repo.RoomTypeRepository;
import com.bsuir.masasha.hostel.core.service.BookingPair;
import com.bsuir.masasha.hostel.core.service.BookingService;
import com.bsuir.masasha.hostel.core.service.cache.ReservationCache;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private ReservationCache cache;

    private Map<RoomType, Pair<Long, BookingPair>> findOption(
            Integer peopleNum, Integer maxCost, String dates,
            BiFunction<RoomType, BookingPair, Pair<Long, BookingPair>> cacheFun,
            BiPredicate<Integer, Integer> costFilter
    ) {
        Map<RoomType, Pair<Long, BookingPair>> options = new LinkedHashMap<>();
        BookingPair bookingTime = splitTime(dates);

        List<RoomType> matchedRoomTypes = roomTypeRepository.findAll().stream()
                .filter(rt -> costFilter.test(rt.getCost(), maxCost))
                .filter(rt -> rt.getSleepPlacesAmount() >= peopleNum)
                .sorted(Comparator.comparing(RoomType::getCost))
                .collect(Collectors.toList());

        matchedRoomTypes.forEach(rt -> {
            Pair<Long, BookingPair> option = cacheFun.apply(rt, bookingTime);
            Optional.ofNullable(option)
                    .ifPresent(o -> options.put(rt, o));
        });

        return options;
    }

    @Override
    public Map<RoomType, Pair<Long, BookingPair>> pickOptions(Integer peopleNum, Integer maxCost, String dates) {
        return findOption(peopleNum, maxCost, dates, cache::findOption, this::lessOrEqualsThenMaxCost);
    }

    @Override
    public Map<RoomType, Pair<Long, BookingPair>> sameDateAlternatives(Integer peopleNum, Integer maxCost, String dates) {
        return findOption(peopleNum, maxCost, dates, cache::findAlternativeDatesOption, this::moreThenMaxCost);
    }

    private boolean lessOrEqualsThenMaxCost(Integer rtCost, Integer maxCost) {
        return rtCost <= maxCost;
    }

    private boolean moreThenMaxCost(Integer rtCost, Integer maxCost) {
        return rtCost <= maxCost;
    }

    private BookingPair splitTime(String dates) {

        return null;
    }

    @Override
    public Map<Reservation, RoomType> otherAlternatives(Integer peopleNum, Integer maxCost, String dates) {
        return null;
    }

    @Override
    public Map<Reservation, RoomType> sameRoomTypeAlternatives(Integer peopleNum, Integer maxCost, String dates) {
        return null;
    }

    //    @Override
//    public Map<RoomType, Pair<Long, BookingPair>> otherAlternatives(Integer peopleNum, Integer maxCost, String dates) {
//        Map<Reservation, RoomType> options = new LinkedHashMap<>();
//
//        BookingPair bookingTime = splitTime(dates);
//
//        List<RoomType> matchedRoomTypes = roomTypeRepository.findAll().stream()
//                .filter(rt -> rt.getCost() > maxCost && rt.getSleepPlacesAmount() >= peopleNum)
//                .sorted(Comparator.comparing(RoomType::getCost))
//                .collect(Collectors.toList());
//
//        matchedRoomTypes.forEach(rt -> {
//            Reservation option = cache.findAlternativeDatesOption(rt, bookingTime);
//            Optional.ofNullable(option)
//                    .ifPresent(o -> options.put(o, rt));
//        });
//        return options;
//    }
//
//    @Override
//    public Map<RoomType, Pair<Long, BookingPair>> sameRoomTypeAlternatives(Integer peopleNum, Integer maxCost, String dates) {
//        Map<Reservation, RoomType> options = new LinkedHashMap<>();
//
//        BookingPair bookingTime = splitTime(dates);
//
//        List<RoomType> matchedRoomTypes = roomTypeRepository.findAll().stream()
//                .filter(rt -> rt.getCost() <= maxCost && rt.getSleepPlacesAmount() >= peopleNum)
//                .sorted(Comparator.comparing(RoomType::getCost))
//                .collect(Collectors.toList());
//
//        matchedRoomTypes.forEach(rt -> {
//            Reservation option = cache.findAlternativeDatesOption(rt, bookingTime);
//            Optional.ofNullable(option)
//                    .ifPresent(o -> options.put(o, rt));
//        });
//
//        return options;
//    }


}
