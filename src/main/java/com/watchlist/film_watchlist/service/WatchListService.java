package com.watchlist.film_watchlist.service;
import com.watchlist.film_watchlist.model.Film;
import com.watchlist.film_watchlist.model.Review;
import com.watchlist.film_watchlist.model.WatchStatus;
import com.watchlist.film_watchlist.model.WatchListItem;
import com.watchlist.film_watchlist.storage.AppStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class WatchListService {

    private final AppStorage appStorage;

    @Autowired
    public WatchListService(AppStorage appStorage) {
        this.appStorage = appStorage;
    }

    public boolean addToWatchList(Film film,WatchStatus status) {
        if (appStorage.containsItem(film.getImdbID())) {
            return false;
        }
        WatchListItem item = new WatchListItem(film, status);
        appStorage.addItem(item);
        return true;
    }

    public List<WatchListItem> getAllItems() {
        return new ArrayList<>(appStorage.getAllItems());
    }

    public WatchListItem getItemById(String id) {
        return appStorage.getItem(id);
    }

    public void updateStatus(String id, WatchStatus newStatus) {
        WatchListItem item = appStorage.getItem(id);
        if(item != null) {
            item.setStatus(newStatus);
            appStorage.updateItem(item);
        }
    }

    public void addOrUpdateReview(String id, int rating, String reviewText) {
        WatchListItem item = appStorage.getItem(id);
        if (item != null) {
            Review review = new Review(rating, reviewText);
            item.setReview(review);
            appStorage.updateItem(item);
        }
    }

    public void deleteItem(String id) {
        appStorage.deleteItem(id);
    }

    public boolean isOnWatchlist(String imdbId) {
        return appStorage.containsItem(imdbId);
    }

    public List<WatchListItem> getItemsByStatus(WatchStatus status) {
        List<WatchListItem> filtered = new ArrayList<>();
        for (WatchListItem item : appStorage.getAllItems()) {
            if (item.getStatus() == status) {
                filtered.add(item);
            }
        }
        return filtered;
    }

    public int getTotalCount() {
        return appStorage.getSize();
    }
}
