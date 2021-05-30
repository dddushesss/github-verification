package com.example.githubclient;

import com.example.githubclient.Model.Issue;
import com.example.githubclient.Services.DatabaseService;
import com.example.githubclient.Services.GithubClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.Collections;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    private DatabaseService databaseService;
    @Autowired
    private GithubClient githubClient;
    private final String TOKEN = System.getenv("telegram_bot_token");
    private final String LOGIN = System.getenv("telegram_bot_login");
    private final ReplyKeyboardMarkup replyKeyboardMarkup;

    public TelegramBot() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton("Вывести всех студентов"));
        keyboardRow.add(new KeyboardButton("Проверить все репозитории"));
        keyboardRow.add(new KeyboardButton("Удалить все коменты"));
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardRow));
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        replyKeyboardMarkup.setResizeKeyboard(true);
    }

    @Override
    public String getBotUsername() {
        return LOGIN;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    private String checkRepos() {
        StringBuilder result = new StringBuilder();

        databaseService.getStudents().forEach(student -> {
            String repo = student.getRepository().substring(student.getRepository().lastIndexOf("/") + 1);
            try {
                githubClient.getPullRequests(student.getGitLogin(), repo).forEach(pull -> {
                    try {
                        githubClient.createIssueComm(new Issue(MessageTemplateVerifier.process(pull.getTitle())), student.getGitLogin(), repo, pull.getNumber());
                    } catch (IOException e) {
                        result.append(e.getMessage()).append("\n");
                    }
                });
            } catch (IOException e) {
                result.append(e.getMessage()).append("\n");
            }
            result.append("Проверен студент ").append(student.getFirs_Name()).append(" ").append(student.getLast_Name()).append("\n");
        });

        return result.toString();
    }

    private String getStudents() {
        StringBuilder result = new StringBuilder();

        databaseService.getStudents().forEach(x -> result.append(x.getFirs_Name()).append(" ").append(x.getLast_Name()).append(" ").append(x.getGitLogin()).append('\n'));
        return result.toString();
    }

    private String deleteAllComments() {
        StringBuilder result = new StringBuilder();
        databaseService.getStudents()
                .forEach(student -> {
                    String repo = student.getRepository().substring(student.getRepository().lastIndexOf("/") + 1);
                    try {
                        githubClient.getRepoIssues(student.getGitLogin(), repo).forEach(issue -> {
                            try {
                                githubClient.deleteReviews(student.getGitLogin(), repo, issue.getId());
                            } catch (IOException e) {
                                result.append(e.getMessage()).append("\n\n");
                            }
                        });
                    } catch (IOException e) {
                        result.append(e.getMessage()).append("\n\n");
                    }
                });

        result.append("Удаление закончено");
        return result.toString();
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            switch (update.getMessage().getText()) {
                case "Вывести всех студентов":
                    sendMessage.setText(getStudents());
                    break;
                case "/start":
                    sendMessage.setText("Тут можно докапываться до студентов");
                    break;
                case "Проверить все репозитории":
                    sendMessage.setText(checkRepos());
                    break;
                case "Удалить все коменты":
                    sendMessage.setText(deleteAllComments());
                    break;
                default:
                    sendMessage.setText("Я не понимат");
                    break;
            }
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
