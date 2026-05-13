package com.nextdigital.nextmusic.catalog.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumJpaRepository extends JpaRepository<AlbumJpaEntity, String> {
}
