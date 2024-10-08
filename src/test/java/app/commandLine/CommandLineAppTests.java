package app.commandLine;


import dialog.DialogManager;
import dialog.ExecutionResult;
import domain.command.Command;
import domain.command.CommandType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.mockito.Mockito.*;


class CommandLineAppTests {

    private static final Map<String, Command> COMMANDS_MAP = new HashMap<>(Map.of(
            "/add", new Command(CommandType.ADD_CARD, null),
            "/help", new Command(CommandType.SHOW_HELP, null),
            "/read", new Command(CommandType.READ_CARD, null),
            "/show", new Command(CommandType.SHOW_ANSWER, null)
    ));

    @Test
    public void ShouldMapCommands() {
        var manager = mock(DialogManager.class);
        when(manager.handleCommand(Mockito.any())).thenReturn(new ExecutionResult("success"));

        var scanner = mock(Scanner.class);

        var app = new CommandLineApp(manager, scanner);

        COMMANDS_MAP.forEach((input, command) -> {
            when(scanner.nextLine()).thenReturn(input);
            app.handleInput();
            verify(manager).handleCommand(command);
        });
    }
}