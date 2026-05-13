package com.nextdigital.nextmusic.library.application;

import com.nextdigital.nextmusic.library.application.port.in.CreatePlaylistUseCase;
import com.nextdigital.nextmusic.library.application.port.out.PlaylistRepository;
import com.nextdigital.nextmusic.library.domain.model.Playlist;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreatePlaylistService implements CreatePlaylistUseCase {

    private final PlaylistRepository playlistRepository;

    public CreatePlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public Playlist createPlaylist(String name, String ownerId) {
        Playlist playlist = Playlist.create(name, ownerId);
        return playlistRepository.save(playlist);
    }
}
