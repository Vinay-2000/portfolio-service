package com.papertrading.portfolio.service;

import com.papertrading.portfolio.entity.User;

public interface UserService {

    User createUser(String username, String password);

    User getUser(Long userId);
}