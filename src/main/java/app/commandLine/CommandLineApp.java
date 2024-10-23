package app.commandLine;

import dialog.UserDialog;
import dialog.commands.CreateCardCommand;
import dialog.commands.HelpCommand;
import dialog.commands.TextInputCommand;
import dialog.commands.abstractions.Command;
import dialog.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;


public class CommandLineApp {
    private static final Map<String, Command> COMMANDS_MAP = new HashMap<>(Map.of(
            "/add", new CreateCardCommand(),
            "/help", new HelpCommand()
    ));
    private final UserDialog userDialog;
    private final Scanner scanner;

    public CommandLineApp(UserDialog userDialog, Scanner scanner) {
        this.userDialog = userDialog;
        this.scanner = scanner;
    }

    public CommandLineApp() {
        this(new UserDialog(new User(UUID.randomUUID())), new Scanner(System.in));
    }

    public void run() {
        showHelp();

        while (true) {
            handleInput();
        }
    }

    public void handleInput() {
        String input = scanner.nextLine();

        var command = COMMANDS_MAP.getOrDefault(input, new TextInputCommand(input));

        var result = userDialog.handleCommand(command);

        System.out.println(result.message());
    }

    private void showHelp() {
        var helpResult = userDialog.handleCommand(COMMANDS_MAP.get("/help"));
        System.out.println(helpResult.message());
    }
}