package app.commandline;

import storage.InMemoryCardRatingStatisticsStorage;
import storage.InMemoryCardStorage;

public class Program {
    public static void main(String[] args) {
        var app = new CommandLineApp(new InMemoryCardStorage(), new InMemoryCardRatingStatisticsStorage());
        app.run();
    }
}
