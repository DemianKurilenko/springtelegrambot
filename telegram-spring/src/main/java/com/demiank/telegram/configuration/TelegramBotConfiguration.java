package com.demiank.telegram.configuration;

import com.demiank.telegram.TelegramBeanProcessor;
import com.demiank.telegram.TelegramBotService;
import com.demiank.telegram.TelegramUserInjectionService;
import com.demiank.telegram.TelegramUserInjectionServiceDefault;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;

public class TelegramBotConfiguration implements ImportAware {

    @Bean
    public TelegramBeanProcessor telegramBeanProcessor(TelegramBotService telegramBotService) {
        return new TelegramBeanProcessor(telegramBotService);
    }

    @Bean
    public TelegramBotService telegramBotService(BotBuilder botBuilder, TelegramUserInjectionService userInjectionService) throws Exception {
        return new TelegramBotService(botBuilder, userInjectionService);
    }

    @ConditionalOnMissingBean(TelegramUserInjectionService.class)
    @Bean
    TelegramUserInjectionService defaultTelegramUserInjectionService() {
        return new TelegramUserInjectionServiceDefault();
    }

    public void setImportMetadata(AnnotationMetadata importMetadata) {
    }
}
