package com.bsuir.masasha.hostel.core.repo;

import com.bsuir.masasha.hostel.core.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long> {
    public Room findByRoomNumber(Integer roomNumber);
}
