package com.music.streaming.user.infrastructure.rest.dto.request;

import com.music.streaming.catalog.domain.Song;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;

@SuperBuilder
@Jacksonized
@Getter
@ToString
public class PostUserRequestDTO {
    final String username;
    final String email;
    final ArrayList<Song> songs;
}
