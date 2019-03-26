package com.bsuir.masasha.hostel.core.service;

import com.bsuir.masasha.hostel.core.domain.User;
import com.bsuir.masasha.hostel.core.domain.dto.BasketEntity;
import com.bsuir.masasha.hostel.core.domain.dto.BookingPair;
import com.bsuir.masasha.hostel.core.service.exception.BookingFailException;
import com.bsuir.masasha.hostel.core.service.exception.BookingPartFailException;
import javafx.util.Pair;

import java.util.Map;


public interface BookingService {

    Map<Long, Pair<Long, BookingPair>> pickOptions(Integer peopleNum, Integer maxCost, String dates);

    Map<Long, Pair<Long, BookingPair>> sameDateAlternatives(Integer peopleNum, Integer maxCost, String dates);

    void book(BasketEntity basketEntity, User user) throws BookingFailException, BookingPartFailException;
}
