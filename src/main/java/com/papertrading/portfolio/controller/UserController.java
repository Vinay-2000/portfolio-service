package com.papertrading.portfolio.controller;

import com.papertrading.portfolio.api.UsersApi;
import com.papertrading.portfolio.api.model.CreateUserRequest;
import com.papertrading.portfolio.api.model.UserResponse;
import com.papertrading.portfolio.entity.User;
import com.papertrading.portfolio.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UsersApi {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserResponse> createUser(
            CreateUserRequest request) {

        User user = userService.createUser(
                request.getUsername(),
                request.getPassword()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponse(user));
    }

    @Override
    public ResponseEntity<UserResponse> getUser(Long userId) {

        User user = userService.getUser(userId);

        return ResponseEntity.ok(toResponse(user));
    }

    private UserResponse toResponse(User user) {

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());
        response.setEnabled(user.getEnabled());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());

        return response;
    }
}