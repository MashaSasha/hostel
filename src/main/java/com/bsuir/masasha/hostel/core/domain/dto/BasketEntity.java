package com.bsuir.masasha.hostel.core.domain.dto;

import com.bsuir.masasha.hostel.core.domain.Bonus;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonAutoDetect
public class BasketEntity implements Serializable {

    private Long roomId;
    private Long roomTypeId;
    private String startDate;
    private String endDate;
    private List<Bonus> bonuses;

    public BasketEntity() {
    }
}
