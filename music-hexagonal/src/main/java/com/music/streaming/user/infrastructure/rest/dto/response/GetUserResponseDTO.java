package com.music.streaming.user.infrastructure.rest.dto.response;

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
public class GetUserResponseDTO {
    final String id;
    final String username;
    final String email;
    final ArrayList<Song> songs;
}
