package com.watchlist.film_watchlist.controller;

import com.watchlist.film_watchlist.service.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    private final WatchListService watchlistService;

    @Autowired
    public HomeController(WatchListService watchlistService) {
        this.watchlistService = watchlistService;
    }


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalFilms", watchlistService.getTotalCount());
        return "index";
    }
}


