package app.commandLine;


import dialog.DialogResponse;
import dialog.UserDialog;
import dialog.commands.CreateCardCommand;
import dialog.commands.HelpCommand;
import dialog.commands.ReadCardCommand;
import dialog.commands.ShowAnswerCommand;
import dialog.commands.abstractions.Command;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.mockito.Mockito.*;


class CommandLineAppTests {

    private static final Map<String, Command> COMMANDS_MAP = new HashMap<>(Map.of(
            "/add", new CreateCardCommand(),
            "/help", new HelpCommand(),
            "/read", new ReadCardCommand(),
            "/show", new ShowAnswerCommand()
    ));

    @Test
    public void shouldMapCommands() {
        var userDialog = mock(UserDialog.class);
        when(userDialog.handleCommand(Mockito.any())).thenReturn(new DialogResponse("success"));

        var scanner = mock(Scanner.class);

        var app = new CommandLineApp(userDialog, scanner);

        COMMANDS_MAP.forEach((input, command) -> {
            when(scanner.nextLine()).thenReturn(input);
            app.handleInput();
            verify(userDialog).handleCommand(Mockito.any(command.getClass()));
        });
    }
}