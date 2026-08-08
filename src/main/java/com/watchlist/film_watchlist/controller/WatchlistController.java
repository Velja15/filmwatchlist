package com.watchlist.film_watchlist.controller;

import com.watchlist.film_watchlist.model.WatchStatus;
import com.watchlist.film_watchlist.model.WatchListItem;
import com.watchlist.film_watchlist.service.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WatchlistController {

    private final WatchListService watchlistService;

    @Autowired
    public WatchlistController(WatchListService watchlistService) {
        this.watchlistService = watchlistService;
    }

    @GetMapping("/watchlist")
    public String showWatchlist(Model model) {
        List<WatchListItem> items = watchlistService.getAllItems();
        model.addAttribute("items", items);
        model.addAttribute("statuses", WatchStatus.values());
        return "watchlist";
    }

    @GetMapping("/watchlist/detail/{id}")
    public String showDetail(@PathVariable("id") String id, Model model) {
        WatchListItem item = watchlistService.getItemById(id);
        if (item == null) {
            return "redirect:/watchlist";
        }
        model.addAttribute("item", item);
        return "film-detail";
    }

    @GetMapping("/watchlist/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        WatchListItem item = watchlistService.getItemById(id);
        if (item == null) {
            return "redirect:/watchlist";
        }
        model.addAttribute("item", item);
        model.addAttribute("statuses", WatchStatus.values());
        return "edit-item";
    }

    @PostMapping("/watchlist/edit/{id}")
    public String updateStatus(@PathVariable("id") String id,
                               @RequestParam("status") WatchStatus status) {
        watchlistService.updateStatus(id, status);
        return "redirect:/watchlist";
    }

    @GetMapping("/watchlist/review/{id}")
    public String showReviewForm(@PathVariable("id") String id, Model model) {
        WatchListItem item = watchlistService.getItemById(id);
        if (item == null) {
            return "redirect:/watchlist";
        }
        model.addAttribute("item", item);
        return "add-review";
    }

    @PostMapping("/watchlist/review/{id}")
    public String addReview(@PathVariable("id") String id,
                            @RequestParam("rating") int rating,
                            @RequestParam("reviewText") String reviewText) {
        watchlistService.addOrUpdateReview(id, rating, reviewText);
        return "redirect:/watchlist/detail/" + id;
    }

    @PostMapping("/watchlist/delete/{id}")
    public String deleteItem(@PathVariable("id") String id) {
        watchlistService.deleteItem(id);
        return "redirect:/watchlist";
    }

}
