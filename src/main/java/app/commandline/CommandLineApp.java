package app.commandline;

import dialog.UserDialog;
import dialog.commands.AddCardCommand;
import dialog.commands.HelpCommand;
import dialog.commands.ReadCardCommand;
import dialog.commands.ShowAnswerCommand;
import dialog.commands.TextInputCommand;
import dialog.commands.abstractions.Command;
import dialog.user.User;
import storage.CardStorage;

import java.util.Map;
import java.util.Scanner;


public class CommandLineApp {
    private final Map<String, Command> commandsMap = Map.of(
            "/add", new AddCardCommand(),
            "/help", new HelpCommand(),
            "/read", new ReadCardCommand(),
            "/show", new ShowAnswerCommand()
    );
    private final UserDialog userDialog;
    private final Scanner scanner;

    public CommandLineApp(UserDialog userDialog, Scanner scanner) {
        this.userDialog = userDialog;
        this.scanner = scanner;
    }

    public CommandLineApp(CardStorage cardStorage) {
        this(new UserDialog(new User(0L), cardStorage),
                new Scanner(System.in));
    }

    public void run() {
        showHelp();

        while (true) {
            handleInput();
        }
    }

    public void handleInput() {
        String input = scanner.nextLine();

        var command = commandsMap.getOrDefault(input, new TextInputCommand(input));

        var result = userDialog.handleCommand(command);

        System.out.println(result.message());
    }

    private void showHelp() {
        var helpResult = userDialog.handleCommand(commandsMap.get("/help"));
        System.out.println(helpResult.message());
    }
}