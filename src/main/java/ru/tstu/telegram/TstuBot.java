package ru.tstu.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class TstuBot extends TelegramLongPollingBot {

    private MessagesService messagesService;

    @Autowired
    public void setMessagesService(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @Override
    public String getBotToken() {
        return "469188883:AAHln3NRW23enC4KTMw7noADY3bw_Gn2pfA";
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

        List<String> replies = messagesService.listReplies();
        Integer i = Math.abs(new Random(new Date().getTime()).nextInt()) % replies.size();
        String reply = replies.get(i);
        SendMessage response = new SendMessage();
        response.setChatId(message.getChatId());
        response.setText(reply);

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
