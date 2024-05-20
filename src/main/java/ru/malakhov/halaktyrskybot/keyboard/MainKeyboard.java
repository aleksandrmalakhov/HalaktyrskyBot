package ru.malakhov.halaktyrskybot.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainKeyboard {

    public static InlineKeyboardMarkup create() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        rows.add(createRow(Map.of("Главная", "/main", "О пляже","/info")));
        rows.add(createRow(Map.of("Погода", "/weather", "Трансфер", "/transfer")));
        rows.add(createRow(Map.of("Серфинг", "/surfing", "Кемпинг","/camping")));
        rows.add(createRow(Map.of("Кафе", "/cafe","Прочее", "/other")));
        rows.add(createRow(Map.of("Помощь", "/help")));

        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }

    private static InlineKeyboardButton createButton(String text, String data) {
        var button = new InlineKeyboardButton(text);
        button.setCallbackData(data);
        return button;
    }

    private static List<InlineKeyboardButton> createRow(Map<String, String> buttonData) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        buttonData.forEach((k, v) -> row.add(createButton(k, v)));

        return row;
    }
}
