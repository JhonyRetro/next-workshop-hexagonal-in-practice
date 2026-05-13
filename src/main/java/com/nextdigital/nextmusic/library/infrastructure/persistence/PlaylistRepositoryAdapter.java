package com.nextdigital.nextmusic.library.infrastructure.persistence;

import com.nextdigital.nextmusic.library.application.port.out.PlaylistRepository;
import com.nextdigital.nextmusic.library.domain.model.Playlist;
import com.nextdigital.nextmusic.library.domain.model.PlaylistId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class PlaylistRepositoryAdapter implements PlaylistRepository {

    private final PlaylistJpaRepository jpaRepository;

    public PlaylistRepositoryAdapter(PlaylistJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Playlist save(Playlist playlist) {
        PlaylistJpaEntity entity = toEntity(playlist);
        PlaylistJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Playlist> findById(PlaylistId playlistId) {
        return jpaRepository.findById(playlistId.value()).map(this::toDomain);
    }

    private PlaylistJpaEntity toEntity(Playlist playlist) {
        return new PlaylistJpaEntity(
            playlist.getId().value(),
            playlist.getName(),
            playlist.getOwnerId(),
            new ArrayList<>(playlist.getTrackIds())
        );
    }

    private Playlist toDomain(PlaylistJpaEntity entity) {
        return new Playlist(
            PlaylistId.of(entity.getId()),
            entity.getName(),
            entity.getOwnerId(),
            entity.getTrackIds()
        );
    }
}
