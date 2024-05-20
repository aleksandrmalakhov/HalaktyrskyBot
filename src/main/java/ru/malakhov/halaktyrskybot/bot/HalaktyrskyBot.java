package ru.malakhov.halaktyrskybot.bot;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.malakhov.halaktyrskybot.config.TelegramBotConfig;
import ru.malakhov.halaktyrskybot.service.SendMessageService;

import static ru.malakhov.halaktyrskybot.components.BotCommands.LIST_OF_COMMAND;

@Component
public class HalaktyrskyBot extends TelegramLongPollingBot {
    private final UpdateHandler updateHandler;
    private final TelegramBotConfig botConfiguration;
    @Getter
    private final SendMessageService sendMessageService;

    public HalaktyrskyBot(UpdateHandler updateHandler,
                          TelegramBotConfig botConfiguration,
                          SendMessageService sendMessageService) {
        this.updateHandler = updateHandler;
        this.botConfiguration = botConfiguration;
        this.sendMessageService = sendMessageService;
    }

    @PostConstruct
    public void init() {
        updateHandler.registerBot(this);
        sendMessageService.registerBot(this);
        try {
            execute(new SetMyCommands(LIST_OF_COMMAND, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return botConfiguration.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfiguration.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        updateHandler.processUpdate(update);
    }
}