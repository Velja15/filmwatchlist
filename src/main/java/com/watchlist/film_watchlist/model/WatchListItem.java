package com.watchlist.film_watchlist.model;

import java.time.LocalDate;

public class WatchListItem {
    private String id;
    private Film film;
    private WatchStatus status;
    private Review review;
    private LocalDate dateAdded;

    public WatchListItem() {
        this.dateAdded = LocalDate.now();
    }

    public WatchListItem(Film film, WatchStatus status) {
        this.id = film.getImdbID();
        this.film = film;
        this.status = status;
        this.review = null;
        this.dateAdded = LocalDate.now();
    }

    public boolean hasReview() {
        return review != null && review.getRating() > 0;
    }

    public String getStatusDisplayName() {
        return status != null ? status.getDisplayName() : "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public WatchStatus getStatus() {
        return status;
    }

    public void setStatus(WatchStatus status) {
        this.status = status;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "WatchlistItem{" +
                "id='" + id + '\'' +
                ", film=" + film.getTitle() +
                ", status=" + status +
                ", dateAdded=" + dateAdded +
                '}';
    }
}
