package ru.tstu.telegram;

import java.util.List;

public interface MessagesService {
    void save(TelegramMessage message);

    List<String> listReplies();
}
