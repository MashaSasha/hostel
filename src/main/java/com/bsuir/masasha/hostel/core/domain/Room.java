package com.bsuir.masasha.hostel.core.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer roomNumber;

    public Room() {
    }

    public Room(Integer roomNumber, Long roomTypeId) {
        this.roomNumber = roomNumber;

    }
}
