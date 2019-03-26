package com.bsuir.masasha.hostel.core.service.cache;

import com.bsuir.masasha.hostel.core.domain.*;
import com.bsuir.masasha.hostel.core.domain.dto.BasketEntityDTO;
import com.bsuir.masasha.hostel.core.domain.dto.BookingPair;
import com.bsuir.masasha.hostel.core.repo.ReservationRepository;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ReservationCacheImpl implements ReservationCache {

    private Map<Long, List<BookingPair>> cache = new ConcurrentHashMap<>();

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    final private ReservationRepository reservationRepository;

    @Autowired
    public ReservationCacheImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Pair<Long, BookingPair> findOption(RoomType roomType, BookingPair bookingTime) {
        List<Room> rooms = roomType.getRooms();
        for (Room room : rooms) {
            List<BookingPair> roomsPairs = cache.get(room.getId());
            if (roomsPairs == null || roomsPairs.isEmpty()) {
                return new Pair<>(room.getId(), bookingTime);
            } else {
                if (!bookingTime.isIntersect(roomsPairs)) {
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

    @Override
    public boolean saveIfFree(BasketEntityDTO basketEntityDTO, PromoCode promo, User user) {
        Long roomId = basketEntityDTO.getRoomId();
        List<BookingPair> roomPairs = cache.get(roomId);
        if (roomPairs == null) {
            roomPairs = new ArrayList<>();
        }

        LocalDate startDate = LocalDate.parse(basketEntityDTO.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(basketEntityDTO.getEndDate(), formatter);
        BookingPair currentBook = new BookingPair(startDate, endDate);

        if (!currentBook.isIntersect(roomPairs)) {
            roomPairs.add(currentBook);
            cache.put(roomId, roomPairs);

            Reservation reservation = new Reservation();

            reservation.setBonuses(basketEntityDTO.getBonuses());
            Double cost;
            if (promo != null) {
                cost = basketEntityDTO.getCost() * (100 - promo.getDiscount()) / 100;
                reservation.setPromoCode(promo);
            } else {
                cost = basketEntityDTO.getCost();
            }
            reservation.setCost(cost);
            reservation.setEarlyBookingSale(basketEntityDTO.getSale());

            reservation.setDays(basketEntityDTO.getDays());
            reservation.setReservationDate(LocalDate.now());
            reservation.setStartDate(startDate);
            reservation.setEndDate(endDate);

            reservation.setUser(user);

            Room room = new Room();
            room.setId(roomId);
            reservation.setRoom(room);

            RoomType roomType = new RoomType();
            roomType.setId(basketEntityDTO.getRoomTypeId());
            reservation.setRoomType(roomType);

            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

}
