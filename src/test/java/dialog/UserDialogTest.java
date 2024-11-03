package dialog;

import dialog.commands.*;
import dialog.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;


public class UserDialogTest {
    @Test
    public void userDialogShouldHandleAddCardScenario() {
        var user = new User(UUID.randomUUID());
        var userDialog = new UserDialog(user);

        mustCreateCard(userDialog, "some question", "some answer");
    }

    @Test
    public void userShouldCanReadCardAndAnswer() {
        var user = new User(UUID.randomUUID());
        var userDialog = new UserDialog(user);
        var question = "Вечный вопрос: сас или сос?";
        var answer = "42";
        mustCreateCard(userDialog, question, answer);

        var readCardResponse = userDialog.handleCommand(new ReadCardCommand());
        Assertions.assertEquals(readCardResponse.message(), question);

        var showAnswerResponse = userDialog.handleCommand(new ShowAnswerCommand());
        Assertions.assertEquals(showAnswerResponse.message(), answer);
    }

    @Test
    public void handleCommandShouldHandleUnknownCommand() {
        var user = new User(UUID.randomUUID());
        var userDialog = new UserDialog(user);

        var response = userDialog.handleCommand(new TextInputCommand("bad command"));

        Assertions.assertTrue(response.message().contains("Неизвестная команда"));
    }

    @Test
    public void handleCommandShouldHandleWrongCommand() {
        var user = new User(UUID.randomUUID());
        var userDialog = new UserDialog(user);
        userDialog.handleCommand(new CreateCardCommand());

        var response = userDialog.handleCommand(new ShowAnswerCommand());

        Assertions.assertTrue(response.message().contains("нельзя выполнить"));

    }

    @Test
    public void handleCommandShouldHandleHelpCommand() {
        var user = new User(UUID.randomUUID());
        var userDialog = new UserDialog(user);

        var response = userDialog.handleCommand(new HelpCommand());

        Assertions.assertTrue(response.message().contains("команды"));
        Assertions.assertTrue(response.message().contains("/add"));
        Assertions.assertTrue(response.message().contains("/help"));
        Assertions.assertTrue(response.message().contains("/read"));
        Assertions.assertTrue(response.message().contains("/show"));
    }


    // Helpers

    private void mustCreateCard(UserDialog userDialog, String question, String answer) {
        var createCardResponse = userDialog.handleCommand(new CreateCardCommand());
        Assertions.assertTrue(createCardResponse.message().contains("Введите вопрос"));

        var questionInputResponse = userDialog.handleCommand(new TextInputCommand(question));
        Assertions.assertTrue(questionInputResponse.message().contains("Введите ответ"));

        var answerInputResponse = userDialog.handleCommand(new TextInputCommand(answer));
        Assertions.assertTrue(answerInputResponse.message().contains("Карта добавлена"));
    }
}

