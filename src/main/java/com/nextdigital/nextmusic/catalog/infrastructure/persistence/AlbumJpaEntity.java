package com.nextdigital.nextmusic.catalog.infrastructure.persistence;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albums")
public class AlbumJpaEntity {

    @Id
    private String id;
    private String title;
    private String artistName;
    private String genre;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "album_id")
    private List<TrackJpaEntity> tracks = new ArrayList<>();

    public AlbumJpaEntity() {}

    public AlbumJpaEntity(String id, String title, String artistName, String genre, List<TrackJpaEntity> tracks) {
        this.id = id;
        this.title = title;
        this.artistName = artistName;
        this.genre = genre;
        this.tracks = tracks != null ? tracks : new ArrayList<>();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public List<TrackJpaEntity> getTracks() { return tracks; }
    public void setTracks(List<TrackJpaEntity> tracks) { this.tracks = tracks; }
}
