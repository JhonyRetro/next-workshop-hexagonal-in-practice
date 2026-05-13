package com.nextdigital.nextmusic.user.application;

import com.nextdigital.nextmusic.user.application.port.in.DeleteUserUseCase;
import com.nextdigital.nextmusic.user.application.port.out.UserRepository;
import com.nextdigital.nextmusic.user.domain.model.UserId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeleteUserService implements DeleteUserUseCase {

    private final UserRepository userRepository;

    public DeleteUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(UserId.of(userId));
    }
}
