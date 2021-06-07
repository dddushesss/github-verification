package com.example.githubclient.Bot;


import com.example.githubclient.Bot.Common.TelegramBotHandler;
import com.example.githubclient.Services.DatabaseService;
import com.example.githubclient.Services.GithubClient;
import com.example.githubclient.Bot.Config.BotConfig;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final DatabaseService databaseService;
    private final GithubClient githubClient;

    public TelegramBot(DatabaseService databaseService, GithubClient githubClient) {
        this.databaseService = databaseService;
        this.githubClient = githubClient;
    }

    @Override
    public String getBotUsername() {
        return BotConfig.getInstance().getLOGIN();
    }

    @Override
    public String getBotToken() {
        return BotConfig.getInstance().getTOKEN();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            TelegramBotHandler handler = new TelegramBotHandler(update, databaseService, githubClient);
            execute(handler.keyboardHandler(update));
        } else if (update.hasCallbackQuery()) {
            TelegramBotHandler handler = new TelegramBotHandler(update, databaseService, githubClient);
            execute(handler.callbackHandler(update));
        }
    }
}

