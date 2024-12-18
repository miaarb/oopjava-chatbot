package dialog;

import dialog.commands.AddCardCommand;
import dialog.commands.HelpCommand;
import dialog.commands.ReadCardCommand;
import dialog.commands.ShowAnswerCommand;
import dialog.commands.StatisticsCommand;
import dialog.commands.TextInputCommand;
import dialog.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import storage.InMemoryCardRatingStatisticsStorage;
import storage.InMemoryCardStorage;

import java.util.Random;


public class UserDialogTests {

    @Test
    public void userDialogShouldHandleAddCardScenario() {
        var user = new User(new Random().nextLong());
        var userDialog = new UserDialog(user, new InMemoryCardStorage(), new InMemoryCardRatingStatisticsStorage());

        mustCreateCard(userDialog, "some question", "some answer");
    }

    @Test
    public void userShouldCanReadCardAndAnswer() {
        var user = new User(new Random().nextLong());
        var userDialog = new UserDialog(user, new InMemoryCardStorage(), new InMemoryCardRatingStatisticsStorage());
        var question = "Вечный вопрос: сас или сос?";
        var answer = "42";
        mustCreateCard(userDialog, question, answer);

        var readCardResponse = userDialog.handleCommand(new ReadCardCommand());
        Assertions.assertEquals(readCardResponse.message(), question);

        var showAnswerResponse = userDialog.handleCommand(new ShowAnswerCommand());
        Assertions.assertTrue(showAnswerResponse.message().contains(answer));
    }

    @Test
    public void userShouldCanRateCard() {
        var user = new User(new Random().nextLong());
        var userDialog = new UserDialog(user, new InMemoryCardStorage(), new InMemoryCardRatingStatisticsStorage());
        var question = "Вечный вопрос: сас или сос?";
        var answer = "42";
        mustCreateCard(userDialog, question, answer);

        userDialog.handleCommand(new ReadCardCommand());
        var showAnswerResponse = userDialog.handleCommand(new ShowAnswerCommand());
        Assertions.assertTrue(showAnswerResponse.message().contains("Оцените"));

        var rateResponse = userDialog.handleCommand(new TextInputCommand("0"));
        Assertions.assertTrue(rateResponse.message().contains("Заново:1"));
    }

    @Test
    public void userShouldCanViewStats() {
        var user = new User(new Random().nextLong());
        var userDialog = new UserDialog(user, new InMemoryCardStorage(), new InMemoryCardRatingStatisticsStorage());
        var question = "Вечный вопрос: сас или сос?";
        var answer = "42";
        mustCreateCard(userDialog, question, answer);

        userDialog.handleCommand(new ReadCardCommand());
        userDialog.handleCommand(new ShowAnswerCommand());
        userDialog.handleCommand(new TextInputCommand("0"));
        var statsResponse = userDialog.handleCommand(new StatisticsCommand());

        Assertions.assertTrue(statsResponse.message().contains("Заново:1") && statsResponse.message().contains(question));
    }

    @Test
    public void handleCommandShouldHandleUnknownCommand() {
        var user = new User(new Random().nextLong());
        var userDialog = new UserDialog(user, new InMemoryCardStorage(), new InMemoryCardRatingStatisticsStorage());

        var response = userDialog.handleCommand(new TextInputCommand("bad command"));

        Assertions.assertTrue(response.message().contains("Неизвестная команда"));
    }

    @Test
    public void handleCommandShouldHandleWrongCommand() {
        var user = new User(new Random().nextLong());
        var userDialog = new UserDialog(user, new InMemoryCardStorage(), new InMemoryCardRatingStatisticsStorage());
        userDialog.handleCommand(new AddCardCommand());

        var response = userDialog.handleCommand(new ShowAnswerCommand());

        Assertions.assertTrue(response.message().contains("нельзя выполнить"));

    }

    @Test
    public void handleCommandShouldHandleHelpCommand() {
        var user = new User(new Random().nextLong());
        var userDialog = new UserDialog(user, new InMemoryCardStorage(), new InMemoryCardRatingStatisticsStorage());

        var response = userDialog.handleCommand(new HelpCommand());

        Assertions.assertTrue(response.message().contains("Команды"));
        Assertions.assertTrue(response.message().contains("/add"));
        Assertions.assertTrue(response.message().contains("/help"));
        Assertions.assertTrue(response.message().contains("/read"));
        Assertions.assertTrue(response.message().contains("/show"));
    }


    // Helpers

    private void mustCreateCard(UserDialog userDialog, String question, String answer) {
        var createCardResponse = userDialog.handleCommand(new AddCardCommand());
        Assertions.assertTrue(createCardResponse.message().contains("Введите вопрос"));

        var questionInputResponse = userDialog.handleCommand(new TextInputCommand(question));
        Assertions.assertTrue(questionInputResponse.message().contains("Введите ответ"));

        var answerInputResponse = userDialog.handleCommand(new TextInputCommand(answer));
        Assertions.assertTrue(answerInputResponse.message().contains("Карта добавлена"));
    }
}

