package ru.malakhov.halaktyrskybot.command.enums;

public enum CommandName {
    MAIN("/main"),
    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    INFO("/info"),
    WEATHER("/weather"),
    TRANSFER("/transfer"),
    SURFING("/surfing"),
    CAMPING("/camping"),
    CAGE("/cafe"),
    OTHER("/other"),
    NO("noCommand"),
    ERROR("error");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}