package ru.malakhov.halaktyrskybot.config;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HalaktyrskyBot extends TelegramLongPollingBot {

    private final String botUsername;
    private final String botToken;

    public HalaktyrskyBot(String botUsername, String botToken) {
        this.botUsername = botUsername;
        this.botToken = botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(handleMessage(messageText));

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private String handleMessage(String messageText) {
        // Обработка сообщений и возвращение ответа
        return switch (messageText.toLowerCase()) {
            case "/start" -> "Добро пожаловать на Халактырский пляж! Чем я могу помочь?";
            case "о пляже" -> "Халактырский пляж славится своим черным песком...";
            case "как добраться" -> "Вы можете добраться до пляжа на автомобиле или общественным транспортом...";
            // Добавьте другие команды и ответы
            default -> "Извините, я не понимаю эту команду.";
        };
    }
}
