package com.watchlist.film_watchlist.storage;

import com.watchlist.film_watchlist.model.WatchListItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class AppStorage {

    private final Map<String, WatchListItem> watchlist = new LinkedHashMap<>();

    public void addItem(WatchListItem item) {
        watchlist.put(item.getId(), item);
    }

    public WatchListItem getItem(String id) {
        return watchlist.get(id);
    }

    public Collection<WatchListItem> getAllItems() {
        return watchlist.values();
    }

    public void deleteItem(String id) {
        watchlist.remove(id);
    }

    public boolean containsItem(String id) {
        return watchlist.containsKey(id);
    }

    public int getSize() {
        return watchlist.size();
    }

    public void clearAll() {
        watchlist.clear();
    }
}
