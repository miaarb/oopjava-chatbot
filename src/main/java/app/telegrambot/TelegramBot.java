package app.telegrambot;

import dialog.UserDialog;
import dialog.commands.AddCardCommand;
import dialog.commands.HelpCommand;
import dialog.commands.ReadCardCommand;
import dialog.commands.ShowAnswerCommand;
import dialog.commands.StatisticsCommand;
import dialog.commands.TextInputCommand;
import dialog.commands.abstractions.Command;
import dialog.user.User;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import storage.CardRatingStatisticsStorage;
import storage.CardStorage;

import java.util.HashMap;
import java.util.Map;

public class TelegramBot implements LongPollingSingleThreadUpdateConsumer {
    private static final Map<String, Command> COMMANDS_MAP = Map.of(
            "/start", new HelpCommand(),
            "/add", new AddCardCommand(),
            "/help", new HelpCommand(),
            "/read", new ReadCardCommand(),
            "/show", new ShowAnswerCommand(),
            "/stats", new StatisticsCommand()
    );
    private final CardStorage cardStorage;
    private final CardRatingStatisticsStorage cardRatingStatisticsStorage;

    private final TelegramClient telegramClient;
    private final Map<Long, UserDialog> userDialogs;

    public TelegramBot(TelegramClient telegramClient, CardStorage cardStorage, CardRatingStatisticsStorage cardRatingStatisticsStorage) {
        this.telegramClient = telegramClient;
        this.cardStorage = cardStorage;
        this.cardRatingStatisticsStorage = cardRatingStatisticsStorage;
        this.userDialogs = new HashMap<>();
    }

    public TelegramBot(
            TelegramClient telegramClient,
            CardStorage cardStorage,
            CardRatingStatisticsStorage cardRatingStatisticsStorage,
            Map<Long, UserDialog> userDialogs
    ) {
        this.telegramClient = telegramClient;
        this.cardStorage = cardStorage;
        this.cardRatingStatisticsStorage = cardRatingStatisticsStorage;
        this.userDialogs = userDialogs;
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            var messageText = update.getMessage().getText();
            var chatId = update.getMessage().getChatId();

            if (!userDialogs.containsKey(chatId)) {
                userDialogs.put(chatId, new UserDialog(new User(chatId), cardStorage, cardRatingStatisticsStorage));
            }

            var dialog = this.userDialogs.get(chatId);

            var command = COMMANDS_MAP.getOrDefault(messageText, new TextInputCommand(messageText));
            var response = dialog.handleCommand(command).message();

            var message = SendMessage
                    .builder()
                    .chatId(chatId)
                    .text(response)
                    .build();
            try {
                telegramClient.execute(message);
            } catch (TelegramApiException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}