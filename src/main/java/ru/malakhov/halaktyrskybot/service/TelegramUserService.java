package ru.malakhov.halaktyrskybot.service;

import ru.malakhov.halaktyrskybot.entity.TelegramUser;

import java.util.List;
import java.util.Optional;

public interface TelegramUserService {
    void save(TelegramUser telegramUser);

    void delete(TelegramUser telegramUser);

    Optional<TelegramUser> findById(Long id);

    Optional<TelegramUser> findByUsername(String username);

    List<TelegramUser> findAll();
}
