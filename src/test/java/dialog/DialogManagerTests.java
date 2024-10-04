package dialog;

import domain.command.Command;
import domain.command.CommandType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DialogManagerTests {

    @Test
    public void handleCommandShouldHandleHelp() {
        var manager = new DialogManager();
        var result = manager.handleCommand(new Command(CommandType.SHOW_HELP, null));
        Assertions.assertTrue(result.message().contains("команды"));
    }

    @Test
    public void handleCommandsShouldHandleAddCard() {
        var manager = new DialogManager();
        var result = manager.handleCommand(new Command(CommandType.ADD_CARD, null));
        Assertions.assertTrue(result.message().contains("Введите вопрос"));
    }

    @Test
    public void handleCommandsShouldHandleAddCardQuestion() {
        var manager = new DialogManager();

        manager.handleCommand(new Command(CommandType.ADD_CARD, null));

        var questionResult = manager.handleCommand(new Command(CommandType.TEXT_MESSAGE, "question"));
        Assertions.assertTrue(questionResult.message().contains("Введите ответ"));
    }

    @Test
    public void handleCommandsShouldHandleAddCardAnswer() {
        var manager = new DialogManager();

        manager.handleCommand(new Command(CommandType.ADD_CARD, null));
        manager.handleCommand(new Command(CommandType.TEXT_MESSAGE, "question"));

        var answerResult = manager.handleCommand(new Command(CommandType.TEXT_MESSAGE, "answer"));
        Assertions.assertTrue(answerResult.message().contains("Карта добавлена"));
    }

    @Test
    public void handleCommandsShouldHandleReadCard() {
        var manager = new DialogManager();

        manager.handleCommand(new Command(CommandType.ADD_CARD, null));
        manager.handleCommand(new Command(CommandType.TEXT_MESSAGE, "question"));
        manager.handleCommand(new Command(CommandType.TEXT_MESSAGE, "answer"));

        var readResult = manager.handleCommand(new Command(CommandType.READ_CARD, null));
        Assertions.assertEquals("question", readResult.message());
    }

    @Test
    public void handleCommandsShouldHandleReadCardWhenNoCards() {
        var manager = new DialogManager();

        var readResult = manager.handleCommand(new Command(CommandType.READ_CARD, null));
        Assertions.assertTrue(readResult.message().contains("добавьте карту"));
    }

    @Test
    public void handleCommandsShouldHandleShowAnswer() {
        var manager = new DialogManager();

        manager.handleCommand(new Command(CommandType.ADD_CARD, null));
        manager.handleCommand(new Command(CommandType.TEXT_MESSAGE, "question"));
        manager.handleCommand(new Command(CommandType.TEXT_MESSAGE, "answer"));
        manager.handleCommand(new Command(CommandType.READ_CARD, null));

        var showResult = manager.handleCommand(new Command(CommandType.SHOW_ANSWER, null));
        Assertions.assertEquals("answer", showResult.message());
    }

    @Test
    public void handleCommandsShouldHandleShowAnswerWhenNoCard() {
        var manager = new DialogManager();

        var showResult = manager.handleCommand(new Command(CommandType.SHOW_ANSWER, null));
        Assertions.assertTrue(showResult.message().contains("откройте карту"));
    }

    @Test
    public void handleCommandsShouldHandleOtherCommandWhenAddCard() {
        var manager = new DialogManager();

        manager.handleCommand(new Command(CommandType.ADD_CARD, null));
        manager.handleCommand(new Command(CommandType.TEXT_MESSAGE, "question"));
        manager.handleCommand(new Command(CommandType.TEXT_MESSAGE, "answer"));
        manager.handleCommand(new Command(CommandType.READ_CARD, null));

        var showResult = manager.handleCommand(new Command(CommandType.SHOW_ANSWER, null));
        Assertions.assertEquals("answer", showResult.message());
    }

    @Test
    public void handleCommandsShouldHandleUnknownCommand() {
        var manager = new DialogManager();

        var result = manager.handleCommand(new Command(CommandType.TEXT_MESSAGE, "/strange command"));

        Assertions.assertEquals("Неизвестная команда", result.message());
    }
}
