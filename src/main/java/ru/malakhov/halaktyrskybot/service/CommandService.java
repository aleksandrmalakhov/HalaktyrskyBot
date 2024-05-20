package ru.malakhov.halaktyrskybot.service;

import ru.malakhov.halaktyrskybot.command.Command;

public interface CommandService {
    Command retrieveCommand(String commandIdentifier);
}