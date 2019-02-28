package com.bsuir.masasha.hostel.core.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    private LocalDate startDate;
    private LocalDate endDate;

//    private List<Bonus> bonuses;

    private Double cost;

}
