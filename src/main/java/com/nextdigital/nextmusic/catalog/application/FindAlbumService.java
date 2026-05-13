package com.nextdigital.nextmusic.catalog.application;

import com.nextdigital.nextmusic.catalog.application.port.in.FindAlbumUseCase;
import com.nextdigital.nextmusic.catalog.application.port.out.AlbumRepository;
import com.nextdigital.nextmusic.catalog.domain.model.Album;
import com.nextdigital.nextmusic.catalog.domain.model.AlbumId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class FindAlbumService implements FindAlbumUseCase {

    private final AlbumRepository albumRepository;

    public FindAlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public Optional<Album> findAlbum(String albumId) {
        return albumRepository.findById(AlbumId.of(albumId));
    }
}
