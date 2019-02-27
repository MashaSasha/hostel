package com.bsuir.masasha.hostel.core.service;

import com.bsuir.masasha.hostel.core.domain.Role;
import com.bsuir.masasha.hostel.core.domain.User;
import com.bsuir.masasha.hostel.core.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username);
    }

    public boolean addUser(User user) {
        if (isUserExist(user.getUsername())) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return true;
    }

    public boolean banUser(User user) {
        if (!isUserExist(user.getUsername())) {
            return false;
        }
        user.setActive(false);
        userRepository.save(user);
        return true;
    }

    private boolean isUserExist(String userName) {
        return userRepository.findByEmail(userName) != null;
    }
}
