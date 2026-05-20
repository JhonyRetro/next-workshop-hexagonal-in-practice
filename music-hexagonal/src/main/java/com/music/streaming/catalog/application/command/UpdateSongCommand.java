package com.music.streaming.catalog.application.command;

import com.music.streaming.catalog.application.port.SongRepository;
import com.music.streaming.catalog.domain.InvalidSongException;
import com.music.streaming.catalog.domain.Song;
import com.music.streaming.catalog.domain.SongNotFoundException;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.springframework.util.StringUtils;

import java.util.UUID;

@SuperBuilder
public class UpdateSongCommand {
    @NonNull
    final SongRepository songRepository;
    @NonNull
    final String id;
    final String title;
    final Integer durationSeconds;
    final String artistId;
    final String albumId;
    final String genreId;

    public void handle() throws SongNotFoundException, InvalidSongException {
        if (!StringUtils.hasText(title)) throw new InvalidSongException();
        if (durationSeconds == null || durationSeconds <= 0) throw new InvalidSongException();
        try {
            UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidSongException();
        }
        if (songRepository.getSongById(id).isEmpty()) throw new SongNotFoundException();
        songRepository.updateSong(Song.builder()
                .id(id)
                .title(title)
                .durationSeconds(durationSeconds)
                .artistId(artistId)
                .albumId(albumId)
                .genreId(genreId)
                .build());
    }
}
