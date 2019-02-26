package com.bsuir.masasha.hostel.repo;

import com.bsuir.masasha.hostel.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
        User findByEmail(String username);
}
