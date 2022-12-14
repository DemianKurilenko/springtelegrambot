package com.demiank.springtelegrambot.user;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final Map<Long, User> users = new HashMap<>();

    public User findUser(Long userId) {
        User userFromStorage = users.get(userId);
        if (userFromStorage != null) {
            return userFromStorage;
        } else {
            User createdUser = new User();
            createdUser.setUserId(userId);
            users.put(userId, createdUser);
            return createdUser;
        }
    }
}
