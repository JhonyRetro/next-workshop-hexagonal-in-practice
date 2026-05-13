package com.nextdigital.nextmusic.catalog.domain.model;

import java.util.List;

public class Album {

    private final AlbumId id;
    private final String title;
    private final String artistName;
    private final Genre genre;
    private final List<Track> tracks;

    public Album(AlbumId id, String title, String artistName, Genre genre, List<Track> tracks) {
        this.id = id;
        this.title = title;
        this.artistName = artistName;
        this.genre = genre;
        this.tracks = tracks != null ? List.copyOf(tracks) : List.of();
    }

    public static Album create(String title, String artistName, Genre genre, List<Track> tracks) {
        return new Album(AlbumId.generate(), title, artistName, genre, tracks);
    }

    public AlbumId getId() { return id; }
    public String getTitle() { return title; }
    public String getArtistName() { return artistName; }
    public Genre getGenre() { return genre; }
    public List<Track> getTracks() { return tracks; }
}
