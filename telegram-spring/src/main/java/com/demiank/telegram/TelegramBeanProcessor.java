package com.demiank.telegram;

import com.demiank.telegram.annotation.EmojiMapping;
import com.demiank.telegram.annotation.TelegramBot;
import com.demiank.telegram.annotation.TelegramCommand;
import com.demiank.telegram.annotation.TelegramMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TelegramBeanProcessor implements BeanPostProcessor, Ordered {

    private TelegramBotService telegramBotService;

    private Map<String, Class> botControllerMap = new HashMap<>();

    public TelegramBeanProcessor(TelegramBotService telegramBotService) {
        this.telegramBotService = telegramBotService;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(TelegramBot.class)) {
            log.info("Init TelegramBot controller: {}", bean.getClass().getName());
            botControllerMap.put(beanName, bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!botControllerMap.containsKey(beanName)) return bean;

        Class original = botControllerMap.get(beanName);

        Arrays.stream(original.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(TelegramCommand.class) || method.isAnnotationPresent(TelegramMessage.class) || method.isAnnotationPresent(EmojiMapping.class))
                .forEach((Method method) -> bindController(bean, method));

        return bean;
    }

    private void bindController(Object bean, Method method) {
        if (method.getAnnotation(TelegramCommand.class) != null) {
            log.info("Init TelegramBot command: {}:{}", method.getAnnotation(TelegramCommand.class), method.getName());
            this.telegramBotService.addHandler(bean, method);
        } else if (method.getAnnotation(TelegramMessage.class) != null) {
            log.info("Init TelegramBot default message handler: {}:{}", method.getAnnotation(TelegramMessage.class), method.getName());
            this.telegramBotService.addMessageHandler(bean, method);
        }
        else if (method.getAnnotation(EmojiMapping.class) != null) {
            log.info("Init telegram emoji mapping: {}:{}", method.getAnnotation(EmojiMapping.class), method.getName());
            this.telegramBotService.addEmojiHandler(bean, method);
        }
        this.telegramBotService.addHelpMethod();
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
