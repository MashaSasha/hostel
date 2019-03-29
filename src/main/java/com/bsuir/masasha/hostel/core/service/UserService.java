package com.bsuir.masasha.hostel.core.service;

import com.bsuir.masasha.hostel.core.domain.Reservation;
import com.bsuir.masasha.hostel.core.domain.Role;
import com.bsuir.masasha.hostel.core.domain.User;
import com.bsuir.masasha.hostel.core.repo.ReservationRepository;
import com.bsuir.masasha.hostel.core.repo.UserRepository;
import com.bsuir.masasha.hostel.core.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    private final ReservationRepository reservationRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public UserService(UserRepository userRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
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

    public void updateUser(MultipartFile image, User updatedUser) {
        String imgPath = ImageUtil.upload(image, uploadPath);

        User userToSave = Optional.ofNullable(updatedUser.getId())
                .flatMap(id -> userRepository.findById(id))
                .map(user -> {
                    user.setName(updatedUser.getName());
                    user.setSecondName(updatedUser.getSecondName());
                    user.setPassport(updatedUser.getPassport());
                    user.setEmail(updatedUser.getEmail());
                    user.setPassword(updatedUser.getPassword());
                    if (!imgPath.isEmpty()) {
                        user.setPassportImage(imgPath);
                    }
                    return user;
                })
                .orElse(updatedUser);

        userRepository.save(userToSave);
    }

    public void deleteReservation(Long reservationId) {
        reservationRepository.delete(new Reservation(reservationId));
    }
}
