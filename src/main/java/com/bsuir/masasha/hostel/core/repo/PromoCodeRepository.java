package com.bsuir.masasha.hostel.core.repo;

import com.bsuir.masasha.hostel.core.domain.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface PromoCodeRepository extends JpaRepository<PromoCode, Long> {

    @Query("select distinct p from PromoCode p")
    List<PromoCode> getPromoNames();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update PromoCode p set p.active = false where p.code = ?1")
    void deactivatePromocode(String code);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update PromoCode p set p.active = true where p.code = ?1")
    void activatePromocode(String code);
}
