package com.bsuir.masasha.hostel.core.repo;

import com.bsuir.masasha.hostel.core.domain.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
