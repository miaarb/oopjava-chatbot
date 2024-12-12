package app.telegrambot;

import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;


public class Program {
    public static void main(String[] args) {
        if (args.length == 0 || args[0].isBlank()) {
            System.out.println("Bot-token in arguments not found or empty");
            return;
        }

        var botToken = args[0];

        try (TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {
            botsApplication.registerBot(botToken, new TelegramBot(new OkHttpTelegramClient(botToken)));
            System.out.println("Bot started");
            Thread.currentThread().join();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}