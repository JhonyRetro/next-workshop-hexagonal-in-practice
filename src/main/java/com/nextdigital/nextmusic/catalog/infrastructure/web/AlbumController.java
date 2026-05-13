package com.nextdigital.nextmusic.catalog.infrastructure.web;

import com.nextdigital.nextmusic.catalog.application.port.in.FindAlbumUseCase;
import com.nextdigital.nextmusic.catalog.application.port.in.RegisterAlbumUseCase;
import com.nextdigital.nextmusic.catalog.domain.model.Album;
import com.nextdigital.nextmusic.catalog.domain.model.Genre;
import com.nextdigital.nextmusic.catalog.domain.model.Track;
import com.nextdigital.nextmusic.catalog.infrastructure.web.dto.AlbumResponse;
import com.nextdigital.nextmusic.catalog.infrastructure.web.dto.RegisterAlbumRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    private final RegisterAlbumUseCase registerAlbumUseCase;
    private final FindAlbumUseCase findAlbumUseCase;

    public AlbumController(RegisterAlbumUseCase registerAlbumUseCase, FindAlbumUseCase findAlbumUseCase) {
        this.registerAlbumUseCase = registerAlbumUseCase;
        this.findAlbumUseCase = findAlbumUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumResponse registerAlbum(@RequestBody RegisterAlbumRequest request) {
        List<Track> tracks = request.tracks().stream()
            .map(t -> Track.of(t.title(), t.durationSeconds()))
            .toList();
        Album album = registerAlbumUseCase.registerAlbum(
            request.title(),
            request.artistName(),
            Genre.valueOf(request.genre().toUpperCase()),
            tracks
        );
        return AlbumResponse.from(album);
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<AlbumResponse> findAlbum(@PathVariable String albumId) {
        return findAlbumUseCase.findAlbum(albumId)
            .map(album -> ResponseEntity.ok(AlbumResponse.from(album)))
            .orElse(ResponseEntity.notFound().build());
    }
}
