package app.telegramBot;

import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;


public class Program {
    public static void main(String[] args) {
        String botToken = "";
        try (TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {
            botsApplication.registerBot(botToken, new TelegramBot());
            System.out.println("Bot successfully started!");
            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}