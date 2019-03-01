package com.bsuir.masasha.hostel.core.service.cache;

import com.bsuir.masasha.hostel.core.domain.RoomType;
import com.bsuir.masasha.hostel.core.service.BookingPair;
import javafx.util.Pair;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ReservationCacheImpl implements ReservationCache {

    private Map<Long, List<BookingPair>> cache;

    @Override
    public synchronized Pair<Long, BookingPair> findOption(RoomType rt, BookingPair bookingTime) {

        // проверяем есть ли там свободное место

        // если есть, то занимаем его и возвращаем объект Reservation



        Optional.of(rt)
                .map(RoomType::getRooms)
                .get()
                .stream()
                .forEach(room -> {
                    bookingTime.isIntersect(cache.get(room.getId()));
                });
        return null;
    }

    @Override
    public Pair<Long, BookingPair> findAlternativeDatesOption(RoomType rt, BookingPair bookingTime) {
        return null;
    }
}
