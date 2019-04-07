package com.bsuir.masasha.hostel.core.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class StatisticDTO implements Serializable {

    private ResponseStatus status;

    private String titleY;
    private String header;

    private List<StatisticLineDTO> dataSeries;
}
