package com.papertrading.portfolio.service;

import com.papertrading.portfolio.dao.UserDao;
import com.papertrading.portfolio.entity.User;
import com.papertrading.portfolio.exception.UsernameAlreadyExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(String username, String password) {

        if (userDao.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }

        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(
                passwordEncoder.encode(password)
        );
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