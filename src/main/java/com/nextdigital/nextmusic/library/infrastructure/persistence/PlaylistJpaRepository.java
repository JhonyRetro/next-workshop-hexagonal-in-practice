package com.nextdigital.nextmusic.library.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistJpaRepository extends JpaRepository<PlaylistJpaEntity, String> {
}
