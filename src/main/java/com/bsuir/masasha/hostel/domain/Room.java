package com.bsuir.masasha.hostel.domain;

import javax.persistence.*;
import java.time.LocalDateTime;


public class Room {

    private Long id;

    private RoomType roomType;

    private Long roomNumber;
    private LocalDateTime checkInDateTime;
    private LocalDateTime evictionDateTime;
}
