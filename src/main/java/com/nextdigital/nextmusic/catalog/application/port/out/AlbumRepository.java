package com.nextdigital.nextmusic.catalog.application.port.out;

import com.nextdigital.nextmusic.catalog.domain.model.Album;
import com.nextdigital.nextmusic.catalog.domain.model.AlbumId;

import java.util.Optional;

public interface AlbumRepository {

    Album save(Album album);

    Optional<Album> findById(AlbumId albumId);
}
