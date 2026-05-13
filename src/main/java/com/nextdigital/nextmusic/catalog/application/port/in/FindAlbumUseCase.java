package com.nextdigital.nextmusic.catalog.application.port.in;

import com.nextdigital.nextmusic.catalog.domain.model.Album;

import java.util.Optional;

public interface FindAlbumUseCase {

    Optional<Album> findAlbum(String albumId);
}
