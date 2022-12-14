package com.demiank.springtelegrambot.configuration;

import com.demiank.springtelegrambot.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class TelegramUserInjectionService implements com.demiank.telegram.TelegramUserInjectionService {

    private final UserService userService;

    public TelegramUserInjectionService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public com.demiank.springtelegrambot.user.User loadUser(org.telegram.telegrambots.meta.api.objects.User user) {
        com.demiank.springtelegrambot.user.User loadedUser = userService.findUser(user.getId());
        loadedUser.setTelegramId(user.getId());
        loadedUser.setTelegramName(user.getFirstName());
        return loadedUser;
    }
}
