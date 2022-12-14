package com.demiank.telegram;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class TelegramUserInjectionServiceDefault implements TelegramUserInjectionService {

    @Override
    public TelegramUser loadUser(User user) {
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setTelegramId(user.getId());
        telegramUser.setTelegramName(user.getFirstName());
        return telegramUser;
    }
}
