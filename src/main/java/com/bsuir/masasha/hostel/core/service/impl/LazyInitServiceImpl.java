package com.bsuir.masasha.hostel.core.service.impl;

import com.bsuir.masasha.hostel.core.domain.User;
import com.bsuir.masasha.hostel.core.repo.UserRepository;
import com.bsuir.masasha.hostel.core.service.LazyInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LazyInitServiceImpl implements LazyInitService {

    private final UserRepository userRepository;

    @Autowired
    public LazyInitServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User fetchReservations(Long userId) {
        User user = userRepository.findByIdAndFetchReservationsEagerly(userId);
        if (user == null) {
            return userRepository.findById(userId).get();
        }
        return user;
    }
}
