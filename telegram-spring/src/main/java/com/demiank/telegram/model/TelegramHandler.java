package com.demiank.telegram.model;

import com.demiank.telegram.annotation.TelegramCommand;
import lombok.Getter;

import java.lang.reflect.Method;

@Getter
public class TelegramHandler {
    private Object bean;
    private Method method;
    private TelegramCommand telegramCommand;

    public TelegramHandler(Object bean, Method method, TelegramCommand telegramCommand) {
        this.bean = bean;
        this.method = method;
        this.telegramCommand = telegramCommand;
    }
}
