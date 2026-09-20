package com.papertrading.portfolio.service;

import com.papertrading.portfolio.dao.UserDao;
import com.papertrading.portfolio.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User createUser(String username, String password) {

        if (userDao.findByUsername(username).isPresent()) {
            throw new RuntimeException(
                    "Username already exists: " + username
            );
        }

        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(password);
        user.setRole("USER");
        user.setEnabled(true);

        return userDao.save(user);
    }

    @Override
    public User getUser(Long userId) {
        return userDao.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found: " + userId
                        ));
    }
}