package com.music.streaming.user.infrastructure.repository.entity;

import com.music.streaming.catalog.domain.Song;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class UserEntity {
    @Id
    private String id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @NonNull
    @Column(nullable = false, unique = true)
    private ArrayList<Song> songs;
}
