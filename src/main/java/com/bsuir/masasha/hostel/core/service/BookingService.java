package com.bsuir.masasha.hostel.core.service;

import com.bsuir.masasha.hostel.core.domain.Reservation;
import com.bsuir.masasha.hostel.core.domain.RoomType;
import javafx.util.Pair;

import java.util.Map;


public interface BookingService {

    Map<RoomType, Pair<Long, BookingPair>> pickOptions(Integer peopleNum, Integer maxCost, String dates);

    Map<RoomType, Pair<Long, BookingPair>> sameDateAlternatives(Integer peopleNum, Integer maxCost, String dates);

    Map<Reservation, RoomType> otherAlternatives(Integer peopleNum, Integer maxCost, String dates);

    Map<Reservation, RoomType> sameRoomTypeAlternatives(Integer peopleNum, Integer maxCost, String dates);
}
