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

    private MessagesService messagesService;

    @Autowired
    public void setMessagesService(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @Override
    public String getBotToken() {
        return "469188883:AAHUlSOqSWOSdFfRTLj41XUw3LTXVzo5mmA";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage())
            return;

        Message message = update.getMessage();

        String userId = message.getFrom().getUserName();
        String text   = message.getText();

        TelegramMessage telegramMessage = new TelegramMessage(userId, text);
        messagesService.save(telegramMessage);

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
