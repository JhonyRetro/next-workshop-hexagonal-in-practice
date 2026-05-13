package com.nextdigital.nextmusic.user.infrastructure.persistence;

import com.nextdigital.nextmusic.user.application.port.out.UserRepository;
import com.nextdigital.nextmusic.user.domain.model.Email;
import com.nextdigital.nextmusic.user.domain.model.User;
import com.nextdigital.nextmusic.user.domain.model.UserId;
import com.nextdigital.nextmusic.user.domain.model.UserPreferences;
import com.nextdigital.nextmusic.user.domain.model.UserProfile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;

    public UserRepositoryAdapter(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = toEntity(user);
        UserJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return jpaRepository.findById(userId.value()).map(this::toDomain);
    }

    @Override
    public void deleteById(UserId userId) {
        jpaRepository.deleteById(userId.value());
    }

    private UserJpaEntity toEntity(User user) {
        return new UserJpaEntity(
            user.getId().value(),
            user.getEmail().value(),
            user.getProfile().firstName(),
            user.getProfile().lastName()
        );
    }

    private User toDomain(UserJpaEntity entity) {
        return new User(
            UserId.of(entity.getId()),
            new Email(entity.getEmail()),
            new UserProfile(entity.getFirstName(), entity.getLastName()),
            UserPreferences.defaultPreferences()
        );
    }
}
