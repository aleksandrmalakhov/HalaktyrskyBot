package ru.malakhov.halaktyrskybot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.malakhov.halaktyrskybot.entity.TelegramUser;

import java.util.Optional;

@Repository
public interface TelegramUserRepository extends CrudRepository<TelegramUser, Long> {
    Optional<TelegramUser> findByUserName(String userName);
}
