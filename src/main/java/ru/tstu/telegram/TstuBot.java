package ru.tstu.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@Component
public class TstuBot extends TelegramLongPollingBot {

    @Autowired
    private MessageService messageService;

    @Override
    public String getBotToken() {
        return "469188883:AAFXteRuh9XzFTPnJas-F43DT5QzZLsYntk";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage())
            return;

        Message message = update.getMessage();

        if (message.getText() != null) {
            String userId = message.getFrom().getUserName();
            String text = message.getText();

            TelegramMessage msg = new TelegramMessage(userId, text);

            messageService.saveMessage(msg);
        }

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
