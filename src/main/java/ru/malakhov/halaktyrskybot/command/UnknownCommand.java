package ru.malakhov.halaktyrskybot.command;

import lombok.NonNull;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.malakhov.halaktyrskybot.service.SendMessageService;

public class UnknownCommand implements Command {
    private final SendMessageService sendMessageService;
    public static final String UNKNOWN_MESSAGE = "Не понимаю вас \uD83D\uDE1F, напишите /help чтобы узнать что я понимаю.";

    public UnknownCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void execute(@NonNull Update update) {
        sendMessageService.answerSendMessage(update, UNKNOWN_MESSAGE);
    }
}
