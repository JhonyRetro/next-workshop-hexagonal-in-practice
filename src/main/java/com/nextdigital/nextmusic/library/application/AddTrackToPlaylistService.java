package com.nextdigital.nextmusic.library.application;

import com.nextdigital.nextmusic.library.application.port.in.AddTrackToPlaylistUseCase;
import com.nextdigital.nextmusic.library.application.port.out.PlaylistRepository;
import com.nextdigital.nextmusic.library.domain.model.Playlist;
import com.nextdigital.nextmusic.library.domain.model.PlaylistId;
import com.nextdigital.nextmusic.shared.domain.DomainException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddTrackToPlaylistService implements AddTrackToPlaylistUseCase {

    private final PlaylistRepository playlistRepository;

    public AddTrackToPlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public Playlist addTrackToPlaylist(String playlistId, String trackId) {
        Playlist playlist = playlistRepository.findById(PlaylistId.of(playlistId))
            .orElseThrow(() -> new DomainException("Playlist not found: " + playlistId));
        playlist.addTrack(trackId);
        return playlistRepository.save(playlist);
    }
}
