package com.demiank.springtelegrambot;


import com.demiank.telegram.annotation.TelegramBot;
import com.demiank.telegram.annotation.TelegramCommand;
import com.demiank.springtelegrambot.user.User;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


@TelegramBot
@RequiredArgsConstructor
public class TelegramBotController {


    @TelegramCommand(value = "/start", description = "Open start menu")
    public SendMessage start(User user, String argument) {
        SendMessage sendMessage = new SendMessage(user.getTelegramId().toString(), "Welcome");
        return sendMessage;
    }

    @TelegramCommand(value = "/testCommand", description = "Some test command")
    public SendMessage testCommand(User user, String argument) {
        SendMessage sendMessage = new SendMessage(user.getTelegramId().toString(), "test command with argument " + argument);
        return sendMessage;
    }


}