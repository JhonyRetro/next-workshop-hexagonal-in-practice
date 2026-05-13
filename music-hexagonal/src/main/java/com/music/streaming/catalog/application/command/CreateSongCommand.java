package com.music.streaming.catalog.application.command;

import com.music.streaming.catalog.application.port.SongRepositoryPort;
import com.music.streaming.catalog.domain.DuplicatedSongException;
import com.music.streaming.catalog.domain.InvalidSongException;
import com.music.streaming.catalog.domain.Song;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.springframework.util.StringUtils;

import java.util.UUID;

@SuperBuilder
public class CreateSongCommand {
    @NonNull
    final SongRepositoryPort songRepository;
    final String title;
    final Integer durationSeconds;
    final String artistId;
    final String albumId;
    final String genreId;

    public String handle() throws InvalidSongException, DuplicatedSongException {
        if (!StringUtils.hasText(title)) throw new InvalidSongException();
        if (durationSeconds == null || durationSeconds <= 0) throw new InvalidSongException();
        if (songRepository.getSongByTitle(title).isPresent()) throw new DuplicatedSongException();
        Song song = Song.builder()
                .id(UUID.randomUUID().toString())
                .title(title)
                .durationSeconds(durationSeconds)
                .artistId(artistId)
                .albumId(albumId)
                .genreId(genreId)
                .build();
        songRepository.createSong(song);
        return song.getId();
    }
}
