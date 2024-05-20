package ru.malakhov.halaktyrskybot.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.malakhov.halaktyrskybot.service.UpdateTextService;

@Component
public class UpdateHandler {
    private HalaktyrskyBot halaktyrskyBot;
    private final UpdateTextService updateTextService;

    public UpdateHandler(UpdateTextService updateTextService) {
        this.updateTextService = updateTextService;
    }

    public void registerBot(HalaktyrskyBot halaktyrskyBot) {
        this.halaktyrskyBot = halaktyrskyBot;
    }

    public void processUpdate(Update update) {
        if (update == null) {
            return;
        }

        if (update.hasMessage()) {
            distributeMessageByType(update);
        } else if (update.hasMyChatMember()) {
            chatUpdated(update);
        } else {
            System.out.println("Unsupported message type is received " + update);
        }
    }

    private void chatUpdated(Update update) {
    }

    private void distributeMessageByType(Update update) {
        var message = update.getMessage();

        if (message.hasText()) {
            updateTextService.processing(update);
        } else if (message.getMigrateToChatId() != null) {

        } else {
            setUnsupportedTypeMessageView(update);
        }
    }

    private void setUnsupportedTypeMessageView(Update update) {
        halaktyrskyBot.getSendMessageService().answerSendMessage(update, "Неподдерживаемый тип сообщения!");
    }
}
