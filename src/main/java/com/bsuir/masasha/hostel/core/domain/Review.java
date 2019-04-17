package com.bsuir.masasha.hostel.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

//@Entity
@Getter
@Setter
public class Review {

    private String title;
    private String data;

    private User user;
//    private List<Like> likes;
}
