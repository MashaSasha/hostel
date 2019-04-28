package com.bsuir.masasha.hostel.core.repo;

import com.bsuir.masasha.hostel.core.domain.Review;
import com.bsuir.masasha.hostel.core.domain.User;
import com.bsuir.masasha.hostel.core.domain.dto.ReviewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select new com.bsuir.masasha.hostel.core.domain.dto.ReviewDTO(" +
            "r," +
            "count(rl)," +
            "sum(case when rl = ?1 then 1 else 0 end) > 0" +
            ") " +
            "from Review r left join r.likes rl " +
            "group by r")
    List<ReviewDTO> findAllDTO(User user);

    @Query("select r from Review r where r.author = ?1")
    Review getReviewByUser(User user);
}
