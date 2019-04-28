package com.bsuir.masasha.hostel.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String text;
    private String type;
    private LocalDate date;
    @OneToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "review_id"),
            inverseJoinColumns = @JoinColumn(name = "user_review_id")
    )
    private Set<User> likes = new HashSet<>();

    public Review() {
    }

    public Review(String title, String type, String text, User author) {
        this.title = title;
        this.type = type;
        this. text = text;
        this.date = LocalDate.now();
        this.author = author;
    }
}
