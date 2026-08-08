package com.watchlist.film_watchlist.controller;

import com.watchlist.film_watchlist.model.Statistics;
import com.watchlist.film_watchlist.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/statistics")
    public String showStatistics(Model model) {
        Statistics stats = statisticsService.calculateStatistics();
        model.addAttribute("stats", stats);
        return "statistics";
    }
}

