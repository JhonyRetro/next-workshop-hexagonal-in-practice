package com.nextdigital.nextmusic.catalog.application;

import com.nextdigital.nextmusic.catalog.application.port.in.RegisterAlbumUseCase;
import com.nextdigital.nextmusic.catalog.application.port.out.AlbumRepository;
import com.nextdigital.nextmusic.catalog.domain.model.Album;
import com.nextdigital.nextmusic.catalog.domain.model.Genre;
import com.nextdigital.nextmusic.catalog.domain.model.Track;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegisterAlbumService implements RegisterAlbumUseCase {

    private final AlbumRepository albumRepository;

    public RegisterAlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public Album registerAlbum(String title, String artistName, Genre genre, List<Track> tracks) {
        Album album = Album.create(title, artistName, genre, tracks);
        return albumRepository.save(album);
    }
}
