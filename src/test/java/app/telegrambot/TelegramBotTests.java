package app.telegrambot;


import dialog.DialogResponse;
import dialog.UserDialog;
import dialog.commands.AddCardCommand;
import dialog.commands.HelpCommand;
import dialog.commands.ReadCardCommand;
import dialog.commands.ShowAnswerCommand;
import dialog.commands.TextInputCommand;
import dialog.commands.abstractions.Command;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;
import java.util.Random;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class TelegramBotTests {

    private static final Map<String, Command> COMMANDS_MAP = Map.of(
            "/start", new HelpCommand(),
            "/add", new AddCardCommand(),
            "/help", new HelpCommand(),
            "/read", new ReadCardCommand(),
            "/show", new ShowAnswerCommand(),
            "some text", new TextInputCommand("some text")
    );

    @Test
    public void shouldMapCommands() throws TelegramApiException {
        var userId = new Random().nextLong();
        var userDialog = mock(UserDialog.class);
        when(userDialog.handleCommand(Mockito.any())).thenReturn(new DialogResponse("success"));

        var telegramClient = mock(OkHttpTelegramClient.class);
        when(telegramClient.execute(Mockito.any(SendMessage.class))).thenReturn(new Message());

        var app = new TelegramBot(telegramClient, Map.of(userId, userDialog));

        var update = mock(Update.class);
        var message = mock(Message.class);

        when(update.hasMessage()).thenReturn(true);
        when(update.getMessage()).thenReturn(message);
        when(message.hasText()).thenReturn(true);
        when(message.getChatId()).thenReturn(userId);

        COMMANDS_MAP.forEach((input, command) -> {
            when(update.getMessage().getText()).thenReturn(input);
            app.consume(update);
            verify(userDialog, Mockito.atLeastOnce()).handleCommand(Mockito.any(command.getClass()));
        });
    }
}