package com.nextdigital.nextmusic.catalog.domain.model;

public class Track {

    private final TrackId id;
    private final String title;
    private final int durationSeconds;

    public Track(TrackId id, String title, int durationSeconds) {
        this.id = id;
        this.title = title;
        this.durationSeconds = durationSeconds;
    }

    public static Track of(String title, int durationSeconds) {
        return new Track(TrackId.generate(), title, durationSeconds);
    }

    public TrackId getId() { return id; }
    public String getTitle() { return title; }
    public int getDurationSeconds() { return durationSeconds; }
}
