package com.nextdigital.nextmusic.library.infrastructure.web;

import com.nextdigital.nextmusic.library.application.port.in.AddTrackToPlaylistUseCase;
import com.nextdigital.nextmusic.library.application.port.in.CreatePlaylistUseCase;
import com.nextdigital.nextmusic.library.domain.model.Playlist;
import com.nextdigital.nextmusic.library.infrastructure.web.dto.AddTrackRequest;
import com.nextdigital.nextmusic.library.infrastructure.web.dto.CreatePlaylistRequest;
import com.nextdigital.nextmusic.library.infrastructure.web.dto.PlaylistResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    private final CreatePlaylistUseCase createPlaylistUseCase;
    private final AddTrackToPlaylistUseCase addTrackToPlaylistUseCase;

    public PlaylistController(CreatePlaylistUseCase createPlaylistUseCase,
                              AddTrackToPlaylistUseCase addTrackToPlaylistUseCase) {
        this.createPlaylistUseCase = createPlaylistUseCase;
        this.addTrackToPlaylistUseCase = addTrackToPlaylistUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlaylistResponse createPlaylist(@RequestBody CreatePlaylistRequest request) {
        Playlist playlist = createPlaylistUseCase.createPlaylist(request.name(), request.ownerId());
        return PlaylistResponse.from(playlist);
    }

    @PostMapping("/{playlistId}/tracks")
    public PlaylistResponse addTrackToPlaylist(@PathVariable String playlistId,
                                               @RequestBody AddTrackRequest request) {
        Playlist playlist = addTrackToPlaylistUseCase.addTrackToPlaylist(playlistId, request.trackId());
        return PlaylistResponse.from(playlist);
    }
}
