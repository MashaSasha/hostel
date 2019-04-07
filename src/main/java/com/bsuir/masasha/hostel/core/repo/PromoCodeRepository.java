package com.bsuir.masasha.hostel.core.repo;

import com.bsuir.masasha.hostel.core.domain.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PromoCodeRepository extends JpaRepository<PromoCode, Long> {

    @Query("select distinct p from PromoCode p")
    List<PromoCode> getPromoNames();

}
