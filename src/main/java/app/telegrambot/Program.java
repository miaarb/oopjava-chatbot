package app.telegrambot;

import storage.InMemoryCardStorage;


public class Program {
    public static void main(String[] args) {
        if (args.length != 1 || args[0].isBlank()) {
            System.out.println("Expected one argument: Bot-token for telegram");
            return;
        }
        var app = new TelegramBotApp(args[0], new InMemoryCardStorage());
        app.run();
    }
}