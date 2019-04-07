package com.bsuir.masasha.hostel.core.repo;

import com.bsuir.masasha.hostel.core.domain.PromoCode;
import com.bsuir.masasha.hostel.core.domain.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

}
