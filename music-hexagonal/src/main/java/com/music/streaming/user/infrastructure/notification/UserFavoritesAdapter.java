package com.music.streaming.user.infrastructure.notification;

import com.music.streaming.catalog.domain.Song;
import com.music.streaming.user.application.port.UserFavoritesPort;
import com.music.streaming.user.domain.User;
import com.music.streaming.user.infrastructure.repository.UserJpaRepository;
import com.music.streaming.user.infrastructure.repository.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor
public class UserFavoritesAdapter implements UserFavoritesPort {
    final UserEntityMapper userEntityMapper;
    final UserJpaRepository userJpaRepository;

    @Override
    public ArrayList<Song> getUserFavorites(User user) {
        return userJpaRepository.findById(user.getId()).get().getSongs();
    }

    @Override
    public void deleteUserFavorites(User user) {
        for (Song song : userJpaRepository.findById(user.getId()).get().getSongs()) {
            userJpaRepository.findById(user.getId()).get().getSongs().remove(song);
        }
    }

    @Override
    public void addUserFavorite(User user, Song song) {
        userJpaRepository.findById(user.getId()).get().getSongs().add(song);
    }

    @Override
    public void deleteUserFavorite(User user, Song song) {
        userJpaRepository.findById(user.getId()).get().getSongs().remove(song);
    }

    @Override
    public void notifyLimit(User user) {
    }
}
