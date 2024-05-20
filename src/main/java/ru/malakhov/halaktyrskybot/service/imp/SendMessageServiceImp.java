package ru.malakhov.halaktyrskybot.service.imp;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.malakhov.halaktyrskybot.bot.HalaktyrskyBot;
import ru.malakhov.halaktyrskybot.components.MessageUtils;
import ru.malakhov.halaktyrskybot.service.SendMessageService;

@Service
public class SendMessageServiceImp implements SendMessageService {
    private HalaktyrskyBot telegramBot;
    private final MessageUtils messageUtils;

    public SendMessageServiceImp(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

    @Override
    public void registerBot(HalaktyrskyBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public void answerSendMessage(Update update, String textMessage) {
        try {
            telegramBot.execute(messageUtils.generateSendMessageWithText(update, textMessage));
        } catch (TelegramApiException e) {
            System.out.println();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void answerSendMessage(SendMessage response) {
        try {
            telegramBot.execute(response);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void answerSendMessage(EditMessageText response) {
        try {
            telegramBot.execute(response);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void answerSendMessage(DeleteMessage response) {
        try {
            telegramBot.executeAsync(response);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }
}
