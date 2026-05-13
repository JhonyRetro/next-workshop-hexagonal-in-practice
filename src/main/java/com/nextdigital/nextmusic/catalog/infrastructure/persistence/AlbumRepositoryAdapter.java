package com.nextdigital.nextmusic.catalog.infrastructure.persistence;

import com.nextdigital.nextmusic.catalog.application.port.out.AlbumRepository;
import com.nextdigital.nextmusic.catalog.domain.model.Album;
import com.nextdigital.nextmusic.catalog.domain.model.AlbumId;
import com.nextdigital.nextmusic.catalog.domain.model.Genre;
import com.nextdigital.nextmusic.catalog.domain.model.Track;
import com.nextdigital.nextmusic.catalog.domain.model.TrackId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AlbumRepositoryAdapter implements AlbumRepository {

    private final AlbumJpaRepository jpaRepository;

    public AlbumRepositoryAdapter(AlbumJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Album save(Album album) {
        AlbumJpaEntity entity = toEntity(album);
        AlbumJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Album> findById(AlbumId albumId) {
        return jpaRepository.findById(albumId.value()).map(this::toDomain);
    }

    private AlbumJpaEntity toEntity(Album album) {
        List<TrackJpaEntity> trackEntities = album.getTracks().stream()
            .map(t -> new TrackJpaEntity(t.getId().value(), t.getTitle(), t.getDurationSeconds()))
            .toList();
        return new AlbumJpaEntity(
            album.getId().value(),
            album.getTitle(),
            album.getArtistName(),
            album.getGenre().name(),
            trackEntities
        );
    }

    private Album toDomain(AlbumJpaEntity entity) {
        List<Track> tracks = entity.getTracks().stream()
            .map(t -> new Track(TrackId.of(t.getId()), t.getTitle(), t.getDurationSeconds()))
            .toList();
        return new Album(
            AlbumId.of(entity.getId()),
            entity.getTitle(),
            entity.getArtistName(),
            Genre.valueOf(entity.getGenre()),
            tracks
        );
    }
}
