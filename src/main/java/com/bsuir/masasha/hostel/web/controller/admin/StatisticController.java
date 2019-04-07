package com.bsuir.masasha.hostel.web.controller.admin;

import com.bsuir.masasha.hostel.core.domain.dto.StatisticDTO;
import com.bsuir.masasha.hostel.core.domain.dto.StatisticLineDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class StatisticController {

    private static final String PROMO_TYPE = "promo";
    private static final String PROFIT_TYPE = "profit";

    @GetMapping("/draw/statistic")
    public StatisticDTO draw(@RequestParam String type, @RequestParam Integer year) {

        switch (type) {
            case PROMO_TYPE:

                break;
            case PROFIT_TYPE:

                break;
        }




        StatisticDTO statisticDTO = new StatisticDTO();
        statisticDTO.setHeader("HEADER");
        statisticDTO.setTitleY("TITLEY");
        statisticDTO.setStatus("SUCCESS");

        List<Double> data = new ArrayList<Double>() {{
            add(1.3);
            add(1.8);
            add(1.1);
            add(0.9);
            add(1.9);
            add(1.1);
            add(1.3);
            add(2.);
            add(1.1);
            add(1.1);
            add(2.1);
            add(1.8);
        }};
        StatisticLineDTO statisticLineDTO = new StatisticLineDTO();
        statisticLineDTO.setData(data);
        statisticLineDTO.setSeriesType("line");
        statisticLineDTO.setCollectionAlias("Промокоды");

        List<StatisticLineDTO> lineDTOS = new ArrayList<>();
        lineDTOS.add(statisticLineDTO);
        statisticDTO.setDataSeries(lineDTOS);

        return statisticDTO;
    }
}
