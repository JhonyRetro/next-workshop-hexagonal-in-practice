package com.nextdigital.nextmusic.user.application;

import com.nextdigital.nextmusic.user.application.port.in.RegisterUserUseCase;
import com.nextdigital.nextmusic.user.application.port.out.UserRepository;
import com.nextdigital.nextmusic.user.domain.model.Email;
import com.nextdigital.nextmusic.user.domain.model.User;
import com.nextdigital.nextmusic.user.domain.model.UserProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepository userRepository;

    public RegisterUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String email, String firstName, String lastName) {
        User user = User.create(new Email(email), new UserProfile(firstName, lastName));
        return userRepository.save(user);
    }
}
