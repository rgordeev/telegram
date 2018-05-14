package ru.tstu.telegram;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class TstuBot extends TelegramLongPollingBot {
    @Override
    public String getBotToken() {
        return "469188883:AAFXteRuh9XzFTPnJas-F43DT5QzZLsYntk";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage())
            return;

        Message message = update.getMessage();

        SendMessage response = new SendMessage();
        response.setChatId(message.getChatId());
        response.setText("Привет!");

        try {
            sendApiMethod(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getBotUsername() {
        return "ExampleTSTUBotBot";
    }
}
