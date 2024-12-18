package app.telegrambot;


import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import storage.CardRatingStatisticsStorage;
import storage.CardStorage;


public class TelegramBotApp {

    private final String botToken;
    private final TelegramBot telegramBot;

    public TelegramBotApp(
            String botToken,
            CardStorage cardStorage,
            CardRatingStatisticsStorage cardRatingStatisticsStorage
    ) {
        this.botToken = botToken;
        this.telegramBot = new TelegramBot(
                new OkHttpTelegramClient(botToken),
                cardStorage,
                cardRatingStatisticsStorage
        );
    }

    public void run() {
        try (var botApplication = new TelegramBotsLongPollingApplication()) {
            botApplication.registerBot(botToken, telegramBot);
            System.out.println("Bot started");
            Thread.currentThread().join();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
