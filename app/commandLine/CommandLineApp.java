package app.commandLine;

import dialog.DialogManager;
import domain.command.CommandType;
import domain.command.Command;

import java.util.HashMap;
import java.util.Map;


public class CommandLineApp {
    private static final Map<String, Command> COMMANDS_MAP = new HashMap<>(Map.of(
            "/add", new Command(CommandType.ADD_CARD, null),
            "/help", new Command(CommandType.SHOW_HELP, null),
            "/read", new Command(CommandType.READ_CARD, null),
            "/show", new Command(CommandType.SHOW_ANSWER, null)
    ));

    public void run() {
        var dialogManager = new DialogManager();

        showHelp(dialogManager);

        var console = System.console();

        while (true) {
            String input = console.readLine();

            var command = COMMANDS_MAP.getOrDefault(input, new Command(CommandType.TEXT_MESSAGE, input));

            var result = dialogManager.handleCommand(command);

            System.out.println(result.message());
        }
    }

    private static void showHelp(DialogManager dialogManager) {
        var helpResult = dialogManager.handleCommand(COMMANDS_MAP.get("/help"));
        System.out.println(helpResult.message());
    }
}