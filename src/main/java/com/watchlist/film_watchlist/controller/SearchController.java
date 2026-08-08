package com.watchlist.film_watchlist.controller;

import com.watchlist.film_watchlist.model.Film;
import com.watchlist.film_watchlist.model.WatchStatus;
import com.watchlist.film_watchlist.service.OmbdService;
import com.watchlist.film_watchlist.service.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    private final OmbdService omdbService;
    private final WatchListService watchlistService;

    @Autowired
    public SearchController(OmbdService omdbService, WatchListService watchlistService) {
        this.omdbService = omdbService;
        this.watchlistService = watchlistService;
    }

    @GetMapping("/search")
    public String showSearchPage(Model model) {
        model.addAttribute("statuses", WatchStatus.values());
        return "search";
    }

    @PostMapping("/search")
    public String searchFilms(@RequestParam("query") String query, Model model) {
        List<Film> results = omdbService.searchFilms(query);
        model.addAttribute("results", results);
        model.addAttribute("query", query);
        model.addAttribute("statuses", WatchStatus.values());

        if (results.isEmpty()) {
            model.addAttribute("message", "Nema rezultata za: " + query);
        }

        return "search";
    }

    @PostMapping("/search/add")
    public String addToWatchlist(@RequestParam("imdbId") String imdbId,
                                 @RequestParam("status") WatchStatus status,
                                 Model model) {
        Film film = omdbService.getFilmById(imdbId);

        if (film != null) {
            boolean added = watchlistService.addToWatchList(film, status);
            if (added) {
                model.addAttribute("successMessage", "Film \"" + film.getTitle() + "\" je dodat na watchlistu!");
            } else {
                model.addAttribute("errorMessage", "Film je vec na watchlisti!");
            }
        } else {
            model.addAttribute("errorMessage", "Greska pri dodavanju filma.");
        }

        model.addAttribute("statuses", WatchStatus.values());
        return "search";
    }

}
