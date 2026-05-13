package com.nextdigital.nextmusic.catalog.application.port.in;

import com.nextdigital.nextmusic.catalog.domain.model.Album;
import com.nextdigital.nextmusic.catalog.domain.model.Genre;
import com.nextdigital.nextmusic.catalog.domain.model.Track;

import java.util.List;

public interface RegisterAlbumUseCase {

    Album registerAlbum(String title, String artistName, Genre genre, List<Track> tracks);
}
