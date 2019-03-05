package com.bsuir.masasha.hostel.core.domain.dto;

import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class BookingOpportunitiesDTO {

    private ResponseStatus status;
    private String message;

    Map<Long, Pair<Long, BookingPair>> options;
    Map<Long, Pair<Long, BookingPair>> alternatives;
}
