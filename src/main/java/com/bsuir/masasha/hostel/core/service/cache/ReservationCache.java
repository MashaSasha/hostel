package com.bsuir.masasha.hostel.core.service.cache;

import com.bsuir.masasha.hostel.core.domain.PromoCode;
import com.bsuir.masasha.hostel.core.domain.RoomType;
import com.bsuir.masasha.hostel.core.domain.User;
import com.bsuir.masasha.hostel.core.domain.dto.BasketEntityDTO;
import com.bsuir.masasha.hostel.core.domain.dto.BookingPair;
import javafx.util.Pair;

public interface ReservationCache {
    Pair<Long, BookingPair> findOption(RoomType rt, BookingPair bookingTime);

    Pair<Long, BookingPair> findAlternativeDatesOption(RoomType rt, BookingPair bookingTime);

    boolean saveIfFree(BasketEntityDTO basketEntityDTO, PromoCode promocode, User user);
}
