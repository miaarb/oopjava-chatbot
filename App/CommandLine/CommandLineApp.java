package App.CommandLine;

import Domain.DialogManager;
import Domain.CommandType;
import Domain.Command;


public class CommandLineApp {
    public void run() {
        var dialogManager = new DialogManager();

        var helpMessage = dialogManager.handleCommand(new Command(CommandType.ShowHelp, null));
        System.out.println(helpMessage);

        var console = System.console();

        while (true) {
            String input = console.readLine();

            var command = switch (input) {
                case "/add" -> new Command(CommandType.AddCard, null);
                case "/help" -> new Command(CommandType.ShowHelp, null);
                case "/read" -> new Command(CommandType.ReadCards, null);
                case "/show" -> new Command(CommandType.ShowAnswer, null);
                default -> new Command(CommandType.TextMessage, input);
            };

            var result = dialogManager.handleCommand(command);
            System.out.println(result);
        }
    }
}