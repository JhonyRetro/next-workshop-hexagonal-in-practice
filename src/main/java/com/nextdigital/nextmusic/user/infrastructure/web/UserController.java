package com.nextdigital.nextmusic.user.infrastructure.web;

import com.nextdigital.nextmusic.user.application.port.in.DeleteUserUseCase;
import com.nextdigital.nextmusic.user.application.port.in.RegisterUserUseCase;
import com.nextdigital.nextmusic.user.domain.model.User;
import com.nextdigital.nextmusic.user.infrastructure.web.dto.RegisterUserRequest;
import com.nextdigital.nextmusic.user.infrastructure.web.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase, DeleteUserUseCase deleteUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerUser(@RequestBody RegisterUserRequest request) {
        User user = registerUserUseCase.registerUser(request.email(), request.firstName(), request.lastName());
        return UserResponse.from(user);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String userId) {
        deleteUserUseCase.deleteUser(userId);
    }
}
