package com.watchlist.film_watchlist.service;

import com.watchlist.film_watchlist.model.Statistics;
import com.watchlist.film_watchlist.model.WatchStatus;
import com.watchlist.film_watchlist.model.WatchListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {

    private final WatchListService watchlistService;

    @Autowired
    public StatisticsService(WatchListService watchlistService) {
        this.watchlistService = watchlistService;
    }

    public Statistics calculateStatistics() {
        List<WatchListItem> allItems = watchlistService.getAllItems();

        int totalFilms = allItems.size();
        int totalWatched = 0;
        int totalWatching = 0;
        int totalPlanned = 0;
        double averageRating = 0.0;
        Map<String, Integer> genreCount = new HashMap<>();

        int ratingSum = 0;
        int ratingCount = 0;

        for (WatchListItem item : allItems) {

            if (item.getStatus() == WatchStatus.POGLEDANO) {
                totalWatched++;
            } else if (item.getStatus() == WatchStatus.GLEDAM) {
                totalWatching++;
            } else if (item.getStatus() == WatchStatus.PLANIRAM) {
                totalPlanned++;
            }

            if (item.hasReview()) {
                ratingSum += item.getReview().getRating();
                ratingCount++;
            }

            String genre = item.getFilm().getGenre();
            if (genre != null && !genre.isEmpty() && !genre.equals("N/A")) {
                String[] genres = genre.split(",");
                for (String g : genres) {
                    String trimmed = g.trim();
                    genreCount.put(trimmed, genreCount.getOrDefault(trimmed, 0) + 1);
                }
            }
        }
        if (ratingCount > 0) {
            averageRating = (double) ratingSum / ratingCount;
        }

        String mostCommonGenre = findMostCommonGenre(genreCount);

        return new Statistics(
                totalFilms,
                totalWatched,
                totalWatching,
                totalPlanned,
                averageRating,
                mostCommonGenre,
                genreCount
        );
    }

    private String findMostCommonGenre(Map<String, Integer> genreCount) {
        if (genreCount.isEmpty()) {
            return "N/A";
        }

        String mostCommon = "";
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : genreCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostCommon = entry.getKey();
            }
        }

        return mostCommon;
    }
}
