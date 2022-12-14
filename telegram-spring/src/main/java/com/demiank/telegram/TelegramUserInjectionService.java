package com.demiank.telegram;


import org.telegram.telegrambots.meta.api.objects.User;

public interface TelegramUserInjectionService {

    TelegramUser loadUser(User user);
}
