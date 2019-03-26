package com.bsuir.masasha.hostel.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomType_id")
    private RoomType roomType;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "bonus_id")
    )
    private List<Bonus> bonuses;

    @OneToOne
    @JoinColumn(name = "promo_code_id", referencedColumnName = "id")
    private PromoCode promoCode;

    private LocalDate reservationDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer days;

    private Double earlyBookingSale;
    private Double cost;
}
