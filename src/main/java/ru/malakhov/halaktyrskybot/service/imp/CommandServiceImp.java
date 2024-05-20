package ru.malakhov.halaktyrskybot.service.imp;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Service;
import ru.malakhov.halaktyrskybot.command.Command;
import ru.malakhov.halaktyrskybot.command.StartCommand;
import ru.malakhov.halaktyrskybot.command.UnknownCommand;
import ru.malakhov.halaktyrskybot.components.MessageUtils;
import ru.malakhov.halaktyrskybot.service.CommandService;
import ru.malakhov.halaktyrskybot.service.SendMessageService;
import ru.malakhov.halaktyrskybot.service.TelegramUserService;

import static ru.malakhov.halaktyrskybot.command.enums.CommandName.START;

@Service
public class CommandServiceImp implements CommandService {
    private final Command unknownCommand;
    private final ImmutableMap<String, Command> commandMap;

    public CommandServiceImp(MessageUtils messageUtils,
                             TelegramUserService userService,
                             SendMessageService sendMessageService) {
        this.commandMap = ImmutableMap.<String, Command>builder()
//                .put(MAIN.getCommandName(), new MainCommand(messageUtils, userService, accountService, sendMessageService))
                .put(START.getCommandName(), new StartCommand(messageUtils, userService, sendMessageService))
//                .put(STOP.getCommandName(), new StopCommand(userService, sendMessageService))
//                .put(HELP.getCommandName(), new HelpCommand(sendMessageService))
//                .put(CLEAN.getCommandName(), new CleanCommand(messageUtils, chatMessageService, sendMessageService))
//                .put(DELETE.getCommandName(), new DeleteMyData(userService, accountService, sendMessageService))
//                .put(NO.getCommandName(), new NoCommand(sendMessageService))
//                .put(ERROR.getCommandName(), new ErrorCommand(sendMessageService))
                .build();
        this.unknownCommand = new UnknownCommand(sendMessageService);
    }

    @Override
    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
