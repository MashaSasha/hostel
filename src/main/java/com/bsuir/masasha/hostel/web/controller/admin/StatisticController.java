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
    private static final String AVG_PROFIT_TYPE = "avgProfit";

    private final StatisticService statisticService;

    @Autowired
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

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
                statisticDTO.setHeader("Общая прибыль в месяц для каждого типа комнат");
                statisticDTO.setTitleY("Прибыль $");

                lines = statisticService.calculateProfitLines(year, false);
                statisticDTO.setDataSeries(lines);
                statisticDTO.setStatus(ResponseStatus.SUCCESS);
                break;
            case AVG_PROFIT_TYPE:
                statisticDTO.setHeader("Прибыль на номер для каждого типа номеров");
                statisticDTO.setTitleY("Прибыль $");

                lines = statisticService.calculateProfitLines(year, true);
                statisticDTO.setDataSeries(lines);
                statisticDTO.setStatus(ResponseStatus.SUCCESS);
                break;
        }

        return statisticDTO;
    }
}
