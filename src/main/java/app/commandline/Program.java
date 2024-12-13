package app.commandline;

import storage.InMemoryCardStorage;

public class Program {
    public static void main(String[] args) {
        var app = new CommandLineApp(new InMemoryCardStorage());
        app.run();
    }
}
