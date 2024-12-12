package app.telegrambot;

import dialog.UserDialog;
import dialog.commands.AddCardCommand;
import dialog.commands.HelpCommand;
import dialog.commands.ReadCardCommand;
import dialog.commands.ShowAnswerCommand;
import dialog.commands.TextInputCommand;
import dialog.commands.abstractions.Command;
import dialog.user.User;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import storage.CardStorage;
import storage.InMemoryCardStorage;

import java.util.HashMap;
import java.util.Map;

public class TelegramBot implements LongPollingSingleThreadUpdateConsumer {
    private static final Map<String, Command> COMMANDS_MAP = Map.of(
            "/start", new HelpCommand(),
            "/add", new AddCardCommand(),
            "/help", new HelpCommand(),
            "/read", new ReadCardCommand(),
            "/show", new ShowAnswerCommand()
    );
    private final TelegramClient telegramClient;
    private final Map<Long, UserDialog> userDialogs;
    private final CardStorage cardStorage = new InMemoryCardStorage();

    public TelegramBot(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
        this.userDialogs = new HashMap<>();
    }

    public TelegramBot(TelegramClient telegramClient, Map<Long, UserDialog> userDialogs) {
        this.telegramClient = telegramClient;
        this.userDialogs = userDialogs;
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            var message_text = update.getMessage().getText();
            var chat_id = update.getMessage().getChatId();

            if (!userDialogs.containsKey(chat_id)) {
                userDialogs.put(chat_id, new UserDialog(new User(chat_id), cardStorage));
            }

            var dialog = this.userDialogs.get(chat_id);

            var command = COMMANDS_MAP.getOrDefault(message_text, new TextInputCommand(message_text));
            var response = dialog.handleCommand(command).message();

            SendMessage message = SendMessage
                    .builder()
                    .chatId(chat_id)
                    .text(response)
                    .build();
            try {
                telegramClient.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}