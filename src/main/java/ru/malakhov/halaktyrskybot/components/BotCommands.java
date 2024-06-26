package ru.malakhov.halaktyrskybot.components;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public interface BotCommands {
    List<BotCommand> LIST_OF_COMMAND = List.of(
            new BotCommand("/start", "start bot"),
            new BotCommand("/stop", "stop bot"),
            new BotCommand("/help", "bot info")
    );
}
