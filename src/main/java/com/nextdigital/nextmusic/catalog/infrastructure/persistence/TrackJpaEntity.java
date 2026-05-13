package com.nextdigital.nextmusic.catalog.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tracks")
public class TrackJpaEntity {

    @Id
    private String id;
    private String title;
    private int durationSeconds;

    public TrackJpaEntity() {}

    public TrackJpaEntity(String id, String title, int durationSeconds) {
        this.id = id;
        this.title = title;
        this.durationSeconds = durationSeconds;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(int durationSeconds) { this.durationSeconds = durationSeconds; }
}
