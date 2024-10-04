package app.commandLine;

import dialog.DialogManager;
import domain.command.CommandType;
import domain.command.Command;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;


public class CommandLineApp {
    private static final Map<String, Command> COMMANDS_MAP = new HashMap<>(Map.of(
            "/add", new Command(CommandType.ADD_CARD, null),
            "/help", new Command(CommandType.SHOW_HELP, null),
            "/read", new Command(CommandType.READ_CARD, null),
            "/show", new Command(CommandType.SHOW_ANSWER, null)
    ));
    private final DialogManager dialogManager;
    private final Console console;

    public CommandLineApp(DialogManager dialogManager, Console console) {
        this.dialogManager = dialogManager;
        this.console = console;
    }

    public CommandLineApp() {
        this(new DialogManager(), System.console());
    }

    public void run() {
        showHelp();

        while (true) {
            handleInput();
        }
    }

    public void handleInput() {
        String input = console.readLine();

        var command = COMMANDS_MAP.getOrDefault(input, new Command(CommandType.TEXT_MESSAGE, input));

        var result = dialogManager.handleCommand(command);

        System.out.println(result.message());
    }

    private void showHelp() {
        var helpResult = dialogManager.handleCommand(COMMANDS_MAP.get("/help"));
        System.out.println(helpResult.message());
    }
}