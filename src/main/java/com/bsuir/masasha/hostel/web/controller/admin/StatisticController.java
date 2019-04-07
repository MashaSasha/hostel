package com.bsuir.masasha.hostel.web.controller.admin;

import com.bsuir.masasha.hostel.core.domain.dto.ResponseStatus;
import com.bsuir.masasha.hostel.core.domain.dto.StatisticDTO;
import com.bsuir.masasha.hostel.core.domain.dto.StatisticLineDTO;
import com.bsuir.masasha.hostel.core.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class StatisticController {

    private static final String PROMO_TYPE = "promo";
    private static final String PROFIT_TYPE = "profit";

    @Autowired
    private StatisticService statisticService;

    @GetMapping("/draw/statistic")
    public StatisticDTO draw(@RequestParam String type, @RequestParam Integer year) {

        StatisticDTO statisticDTO = new StatisticDTO();
        List<StatisticLineDTO> lines;
        switch (type) {
            case PROMO_TYPE:
                statisticDTO.setHeader("Использовано промокодов в месяц");
                statisticDTO.setTitleY("Колличество ед.");

                lines = statisticService.calculatePromoLines(year);
                statisticDTO.setDataSeries(lines);
                statisticDTO.setStatus(ResponseStatus.SUCCESS);
                break;
            case PROFIT_TYPE:
                statisticDTO.setHeader("Прибыль в месяц для каждого типа комнат");
                statisticDTO.setTitleY("Прибыль $");

                lines = statisticService.calculateProfitLines(year);
                statisticDTO.setDataSeries(lines);
                statisticDTO.setStatus(ResponseStatus.SUCCESS);
                break;
        }

//        List<Double> data = new ArrayList<Double>() {{
//            add(1.3);
//            add(1.8);
//            add(1.1);
//            add(0.9);
//            add(1.9);
//            add(1.1);
//            add(1.3);
//            add(2.);
//            add(1.1);
//            add(1.1);
//            add(2.1);
//            add(1.8);
//        }};
//        StatisticLineDTO statisticLineDTO = new StatisticLineDTO();
//        statisticLineDTO.setData(data);
//        statisticLineDTO.setSeriesType("line");
//        statisticLineDTO.setCollectionAlias("Промокоды");
//
//        List<StatisticLineDTO> lineDTOS = new ArrayList<>();
//        lineDTOS.add(statisticLineDTO);
//        statisticDTO.setDataSeries(lineDTOS);

        return statisticDTO;
    }
}
