package com.bsuir.masasha.hostel.core.domain.dto;

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
        for (BookingPair pair: pairs) {
            LocalDate st = pair.getStartDate();
            LocalDate end = pair.getEndDate();
            boolean isIntersect =
                    startDate.isAfter(st) && startDate.isBefore(end)
                    || endDate.isAfter(st) && endDate.isBefore(end)
                    || startDate.isBefore(st) && endDate.isAfter(end);
            if (isIntersect) {
                return false;
            }
        }
        return false;
    }
}
