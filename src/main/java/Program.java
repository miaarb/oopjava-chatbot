import app.commandline.CommandLineApp;
import app.telegrambot.TelegramBotApp;
import storage.InMemoryCardStorage;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        var argsList = new ArrayList<String>();
        var launchTelegramBot = false;
        var launchCommandLineApp = false;

        for (String arg : args) {
            switch (arg) {
                case "--console":
                    launchCommandLineApp = true;
                    break;
                case "--telegram":
                    launchTelegramBot = true;
                    break;
                default:
                    argsList.add(arg);
                    break;
            }
        }

        var cardStorage = new InMemoryCardStorage();
        var runningApps = new ArrayList<Thread>();

        if (launchTelegramBot) {
            if (argsList.size() != 1 || argsList.getFirst().isBlank()) {
                System.out.println("Expected one argument: Bot-token for telegram");
                return;
            }
            var app = new TelegramBotApp(argsList.getFirst(), cardStorage);
            runningApps.add(new Thread(app::run));
        }

        if (launchCommandLineApp) {
            var app = new CommandLineApp(cardStorage);
            runningApps.add(new Thread(app::run));
        }

        if (runningApps.isEmpty()) {
            System.out.println("Please choose at least one option: --console or --telegram");
        }

        startThreads(runningApps);
        waitThreads(runningApps);
    }

    private static void startThreads(ArrayList<Thread> threads) {
        for (var thread : threads) {
            thread.start();
        }
    }

    private static void waitThreads(ArrayList<Thread> threads) {
        for (var thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
