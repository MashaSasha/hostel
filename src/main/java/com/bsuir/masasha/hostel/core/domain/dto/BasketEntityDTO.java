package com.bsuir.masasha.hostel.core.domain.dto;

import com.bsuir.masasha.hostel.core.domain.Bonus;
import com.bsuir.masasha.hostel.core.domain.RoomType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonAutoDetect
public class BasketEntityDTO implements Serializable {

    private Long roomId;
    private Long roomTypeId;
    private String startDate;
    private String endDate;
    private Integer sale;
    private Integer days;
    private List<Bonus> bonuses;

    private RoomType roomType;

    public BasketEntityDTO() {
    }
}
