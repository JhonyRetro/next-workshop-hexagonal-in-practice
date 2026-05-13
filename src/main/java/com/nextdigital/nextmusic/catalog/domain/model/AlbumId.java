package com.nextdigital.nextmusic.catalog.domain.model;

import java.util.UUID;

public record AlbumId(String value) {

    public static AlbumId generate() {
        return new AlbumId(UUID.randomUUID().toString());
    }

    public static AlbumId of(String value) {
        return new AlbumId(value);
    }
}
