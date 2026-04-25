package com.watchlist.film_watchlist.model;

import java.util.Map;

public class Statistics {

    private int totalFilms;
    private int totalWatched;
    private int totalWatching;
    private int totalPlanned;
    private double averageRating;
    private String mostCommonGenre;
    private Map<String,Integer> genreCount;

    public Statistics() {
        this.totalFilms = 0;
        this.totalWatched = 0;
        this.totalWatching = 0;
        this.totalPlanned = 0;
        this.averageRating = 0.0;
        this.mostCommonGenre = "N/A";
    }

    public Statistics(int totalFilms, int totalWatched, int totalWatching,
                      int totalPlanned, double averageRating,
                      String mostCommonGenre, Map<String, Integer> genreCount) {
        this.totalFilms = totalFilms;
        this.totalWatched = totalWatched;
        this.totalWatching = totalWatching;
        this.totalPlanned = totalPlanned;
        this.averageRating = averageRating;
        this.mostCommonGenre = mostCommonGenre;
        this.genreCount = genreCount;
    }

    public String getFormattedAverageRating() {
        if (averageRating == 0.0) {
            return "N/A";
        }
        return String.format("%.1f", averageRating);
    }

    public boolean hasData() {
        return totalFilms > 0;
    }

    public int getTotalFilms() {
        return totalFilms;
    }

    public void setTotalFilms(int totalFilms) {
        this.totalFilms = totalFilms;
    }

    public int getTotalWatched() {
        return totalWatched;
    }

    public void setTotalWatched(int totalWatched) {
        this.totalWatched = totalWatched;
    }

    public int getTotalWatching() {
        return totalWatching;
    }

    public void setTotalWatching(int totalWatching) {
        this.totalWatching = totalWatching;
    }

    public int getTotalPlanned() {
        return totalPlanned;
    }

    public void setTotalPlanned(int totalPlanned) {
        this.totalPlanned = totalPlanned;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getMostCommonGenre() {
        return mostCommonGenre;
    }

    public void setMostCommonGenre(String mostCommonGenre) {
        this.mostCommonGenre = mostCommonGenre;
    }

    public Map<String, Integer> getGenreCount() {
        return genreCount;
    }

    public void setGenreCount(Map<String, Integer> genreCount) {
        this.genreCount = genreCount;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "totalFilms=" + totalFilms +
                ", totalWatched=" + totalWatched +
                ", averageRating=" + averageRating +
                ", mostCommonGenre='" + mostCommonGenre + '\'' +
                '}';
    }
}
