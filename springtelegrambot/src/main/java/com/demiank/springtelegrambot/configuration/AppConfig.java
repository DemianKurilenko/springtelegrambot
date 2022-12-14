package com.demiank.springtelegrambot.configuration;

import com.demiank.telegram.annotation.EnableTelegramBot;
import com.demiank.telegram.configuration.BotBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableTelegramBot
@Configuration
public class AppConfig {

    private final String botName;
    private final String botToken;

    public AppConfig(@Value("${bot.name}")String botName, @Value("${bot.token}")String botToken) {
        this.botName = botName;
        this.botToken = botToken;
    }

    @Bean
    public BotBuilder telegramBotBuilder() {
        return new BotBuilder()
                .username(botName)
                .token(botToken);
    }
}