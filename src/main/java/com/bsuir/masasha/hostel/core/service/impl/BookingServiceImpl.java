package com.bsuir.masasha.hostel.core.service.impl;

import com.bsuir.masasha.hostel.core.domain.RoomType;
import com.bsuir.masasha.hostel.core.repo.RoomTypeRepository;
import com.bsuir.masasha.hostel.core.domain.dto.BookingPair;
import com.bsuir.masasha.hostel.core.service.BookingService;
import com.bsuir.masasha.hostel.core.service.cache.ReservationCache;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final RoomTypeRepository roomTypeRepository;

    private final ReservationCache cache;

    @Autowired
    public BookingServiceImpl(RoomTypeRepository roomTypeRepository, ReservationCache cache) {
        this.roomTypeRepository = roomTypeRepository;
        this.cache = cache;
    }

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    private Map<RoomType, Pair<Long, BookingPair>> findOption(
            Integer peopleNum,
            Integer maxCost,
            String dates,
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
            Pair<Long, BookingPair> option = cache.findOption(rt, bookingTime);
            Optional.ofNullable(option)
                    .ifPresent(o -> options.put(rt, o));
        });

        return options;
    }

    @Override
    public Map<RoomType, Pair<Long, BookingPair>> pickOptions(Integer peopleNum, Integer maxCost, String dates) {
        return findOption(peopleNum, maxCost, dates, this::lessOrEqualsThenMaxCost);
    }

    @Override
    public Map<RoomType, Pair<Long, BookingPair>> sameDateAlternatives(Integer peopleNum, Integer maxCost, String dates) {
        return findOption(peopleNum, maxCost, dates, this::moreThenMaxCost);
    }

    private boolean lessOrEqualsThenMaxCost(Integer rtCost, Integer maxCost) {
        return rtCost <= maxCost;
    }

    private boolean moreThenMaxCost(Integer rtCost, Integer maxCost) {
        return rtCost <= maxCost;
    }

    private BookingPair splitTime(String dates) {
        String[] splitDates = dates.split(" - ");
        LocalDate start = LocalDate.parse(splitDates[0]);
        LocalDate end = LocalDate.parse(splitDates[1]);
        return new BookingPair(start, end);
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
