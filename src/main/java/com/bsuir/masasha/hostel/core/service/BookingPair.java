package com.bsuir.masasha.hostel.core.service;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class BookingPair {

    private LocalDate startDate;
    private LocalDate endDate;

    public BookingPair() {
    }

    public BookingPair(LocalDate start, LocalDate end) {
        this.startDate = start;
        this.endDate = end;
    }

    public boolean isIntersect(List<BookingPair> pairs) {

        return false;
    }
}
