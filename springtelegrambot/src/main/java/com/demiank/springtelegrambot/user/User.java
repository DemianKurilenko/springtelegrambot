package com.demiank.springtelegrambot.user;

import com.demiank.telegram.TelegramUser;

public class User extends TelegramUser {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
