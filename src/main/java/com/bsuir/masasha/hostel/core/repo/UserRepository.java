package com.bsuir.masasha.hostel.core.repo;

import com.bsuir.masasha.hostel.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
        User findByEmail(String username);

        @Query("select u from User u join fetch u.reservations where u.id = (:id)")
        User findByIdAndFetchReservationsEagerly(@Param("id") Long id);
}
