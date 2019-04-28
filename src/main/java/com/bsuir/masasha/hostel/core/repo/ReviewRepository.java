package com.bsuir.masasha.hostel.core.repo;

import com.bsuir.masasha.hostel.core.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
