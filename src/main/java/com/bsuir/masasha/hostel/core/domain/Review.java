package com.bsuir.masasha.hostel.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String text;
    private LocalDate date;
    @Column(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "review_id"),
            inverseJoinColumns = @JoinColumn(name = "user_review_id")
    )
    private List<User> likes;
}
