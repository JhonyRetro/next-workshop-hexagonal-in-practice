package com.music.streaming.user.application.port;

import com.music.streaming.catalog.domain.Song;
import com.music.streaming.user.domain.User;

import java.util.ArrayList;

public interface UserFavoritesPort {
    ArrayList<Song> getUserFavorites(User user);
    void deleteUserFavorites(User user);
    void addUserFavorite(User user, Song song);
    void deleteUserFavorite(User user, Song song);
    void notifyLimit(User user);
}

