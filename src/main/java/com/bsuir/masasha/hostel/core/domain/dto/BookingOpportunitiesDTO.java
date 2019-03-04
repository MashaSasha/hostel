package com.bsuir.masasha.hostel.core.domain.dto;

import com.bsuir.masasha.hostel.core.domain.RoomType;
import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class BookingOpportunitiesDTO {

    private ResponseStatus status;
    private String message;

    Map<RoomType, Pair<Long, BookingPair>> reservations;
    Map<RoomType, Pair<Long, BookingPair>> alternatives;
}
