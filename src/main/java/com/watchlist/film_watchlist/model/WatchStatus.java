package com.watchlist.film_watchlist.model;

public enum WatchStatus {

    PLANIRAM("Planiram da gledam"),
    GLEDAM("Trenutno gledam"),
    POGLEDANO("Pogledano");

    private final String displayName;

    WatchStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
