package com.bsuir.masasha.hostel.core.service.cache;

import com.bsuir.masasha.hostel.core.domain.Room;
import com.bsuir.masasha.hostel.core.domain.RoomType;
import com.bsuir.masasha.hostel.core.domain.dto.BookingPair;
import javafx.util.Pair;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ReservationCacheImpl implements ReservationCache {

    private Map<Long, List<BookingPair>> cache = new ConcurrentHashMap<>();

    @Override
    public Pair<Long, BookingPair> findOption(RoomType roomType, BookingPair bookingTime) {
        List<Room> rooms = roomType.getRooms();
        for (Room room: rooms) {
            List<BookingPair> roomsPairs = cache.get(room.getId());
            if (!roomsPairs.isEmpty()) {
                boolean isIntersecting = bookingTime.isIntersect(roomsPairs);
                if (!isIntersecting) {
                    roomsPairs.add(bookingTime);
                    return new Pair<>(room.getId(), bookingTime);
                }
            }
        }
        return null;
    }

    @Override
    public Pair<Long, BookingPair> findAlternativeDatesOption(RoomType rt, BookingPair bookingTime) {
        return null;
    }
}
