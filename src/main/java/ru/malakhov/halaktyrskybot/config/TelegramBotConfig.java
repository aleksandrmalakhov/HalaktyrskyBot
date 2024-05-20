package ru.malakhov.halaktyrskybot.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class TelegramBotConfig {
    @Value("${telegram.bot.username}")
    private String botName;

    @Value("${telegram.bot.token}")
    private String botToken;
}