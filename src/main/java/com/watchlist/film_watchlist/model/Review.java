package com.watchlist.film_watchlist.model;

import java.time.LocalDate;

public class Review {

    private int rating;
    private String reviewText;
    private LocalDate dateWritten;

    public Review() {
        this.dateWritten = LocalDate.now();
    }

    public Review(int rating, String reviewText) {
        this.rating = rating;
        this.reviewText = reviewText;
        this.dateWritten = LocalDate.now();
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating < 1 || rating > 10){
            throw new IllegalArgumentException("Ocena mora biti izmedju 1 i 10");
        }
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public LocalDate getDateWritten() {
        return dateWritten;
    }

    public void setDateWritten(LocalDate dateWritten) {
        this.dateWritten = dateWritten;
    }

    @Override
    public String toString() {
        return "Review{" +
                "rating=" + rating +
                ", reviewText='" + reviewText + '\'' +
                ", dateWritten=" + dateWritten +
                '}';
    }
}
