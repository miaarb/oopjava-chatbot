package app.telegramBot;

import dialog.UserDialog;
import dialog.commands.*;
import dialog.commands.abstractions.Command;
import dialog.user.User;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TelegramBot implements LongPollingSingleThreadUpdateConsumer {
    private TelegramClient telegramClient = new OkHttpTelegramClient("");
    private Map<Long, UserDialog> userDialogs = new HashMap<>();
    private static final Map<String, Command> COMMANDS_MAP = Map.of(
            "/add", new CreateCardCommand(),
            "/help", new HelpCommand(),
            "/read", new ReadCardCommand(),
            "/show", new ShowAnswerCommand()
    );

    @Override
    public void consume(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            if (!userDialogs.containsKey(chat_id)) {
                userDialogs.put(chat_id, new UserDialog(new User(UUID.randomUUID())));
            }
            var dialog = this.userDialogs.get(chat_id);

            var command = COMMANDS_MAP.getOrDefault(message_text, new TextInputCommand(message_text));
            var response = dialog.handleCommand(command);

            SendMessage message = SendMessage // Create a message object
                    .builder()
                    .chatId(chat_id)
                    .text(response.message())
                    .build();
            try {
                telegramClient.execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}