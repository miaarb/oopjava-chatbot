package app.commandLine;


import dialog.DialogManager;
import dialog.DialogResponse;
import domain.commands.ExecutableCommand;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.mockito.Mockito.*;


class CommandLineAppTests {

    private static final Map<String, ExecutableCommand> COMMANDS_MAP = new HashMap<>(Map.of(
            "/add", new ExecutableCommand(CommandType.ADD_CARD, null),
            "/help", new ExecutableCommand(CommandType.SHOW_HELP, null),
            "/read", new ExecutableCommand(CommandType.READ_CARD, null),
            "/show", new ExecutableCommand(CommandType.SHOW_ANSWER, null)
    ));

    @Test
    public void ShouldMapCommands() {
        var manager = mock(DialogManager.class);
        when(manager.handleCommand(Mockito.any())).thenReturn(new DialogResponse("success"));

        var scanner = mock(Scanner.class);

        var app = new CommandLineApp(manager, scanner);

        COMMANDS_MAP.forEach((input, command) -> {
            when(scanner.nextLine()).thenReturn(input);
            app.handleInput();
            verify(manager).handleCommand(command);
        });
    }
}