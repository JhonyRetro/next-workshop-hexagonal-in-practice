package com.nextdigital.nextmusic.library.infrastructure.persistence;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playlists")
public class PlaylistJpaEntity {

    @Id
    private String id;
    private String name;
    private String ownerId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "playlist_tracks", joinColumns = @JoinColumn(name = "playlist_id"))
    @Column(name = "track_id")
    private List<String> trackIds = new ArrayList<>();

    public PlaylistJpaEntity() {}

    public PlaylistJpaEntity(String id, String name, String ownerId, List<String> trackIds) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.trackIds = trackIds != null ? trackIds : new ArrayList<>();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getOwnerId() { return ownerId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }

    public List<String> getTrackIds() { return trackIds; }
    public void setTrackIds(List<String> trackIds) { this.trackIds = trackIds; }
}
