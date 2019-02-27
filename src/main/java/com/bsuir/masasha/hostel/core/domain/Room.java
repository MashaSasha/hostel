package com.bsuir.masasha.hostel.core.domain;


import javax.persistence.*;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_roomType")
    private RoomType roomType;

    private Long roomNumber;


}
