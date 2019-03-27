package com.bsuir.masasha.hostel.core.service;

import com.bsuir.masasha.hostel.core.domain.User;

public interface LazyInitService {

    public User fetchReservations(Long userId);
}
