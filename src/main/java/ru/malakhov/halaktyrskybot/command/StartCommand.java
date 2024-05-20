package ru.malakhov.halaktyrskybot.command;

import lombok.NonNull;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.malakhov.halaktyrskybot.components.MessageUtils;
import ru.malakhov.halaktyrskybot.entity.TelegramUser;
import ru.malakhov.halaktyrskybot.keyboard.MainKeyboard;
import ru.malakhov.halaktyrskybot.service.SendMessageService;
import ru.malakhov.halaktyrskybot.service.TelegramUserService;

public class StartCommand implements Command{
    private final MessageUtils messageUtils;
    private final TelegramUserService userService;
    private final SendMessageService sendMessageService;

    public StartCommand(MessageUtils messageUtils,
                        TelegramUserService userService,
                        SendMessageService sendMessageService) {
        this.messageUtils = messageUtils;
        this.userService = userService;
        this.sendMessageService = sendMessageService;
    }


    @Override
    public void execute(@NonNull Update update) {
        var chatId = update.getMessage().getChatId();
        var telegramUser = update.getMessage().getFrom();
        var persistentAppUser = userService.findById(telegramUser.getId());

        persistentAppUser.ifPresentOrElse((user) -> {
            //TODO подумать про создание аккаунта
            //сделать отправку сообщений в отдельный поток???
            sendMessageService.answerSendMessage(update, user.getFirstName() + ", с возвращением!");

            if (!user.isActive()) {
                user.setActive(true);

                userService.save(user);
            }
        }, () -> {
            //сделать отправку сообщений в отдельный поток???
            sendMessageService.answerSendMessage(update, telegramUser.getFirstName() + ", добро пожаловать!");

            var transientAppUser = new TelegramUser.Builder()
                    .id(telegramUser.getId())
                    .userName(telegramUser.getUserName())
                    .firstName(telegramUser.getFirstName())
                    .lastName(telegramUser.getLastName())
                    .isActive(true)
                    .build();

            userService.save(transientAppUser);
        });

//        var mainMessage = messageUtils.createSendMessageWithKeyboard(chatId, MAIN_TEXT, MainKeyboard.create());
//        sendMessageService.answerSendMessage(mainMessage);
    }
}
