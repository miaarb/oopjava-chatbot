package dialog;

import dialog.commandExecutors.addCard.AddCardExecutor;
import dialog.commandExecutors.addCard.AnswerInputCommandExecutor;
import dialog.commandExecutors.addCard.QuestionInputCommandExecutor;
import dialog.commandExecutors.readCard.ReadCardExecutor;
import dialog.commandExecutors.readCard.ShowAnswerExecutor;
import dialog.commandExecutors.showHelp.HelpCommandExecutor;
import dialog.commands.*;
import dialog.commands.abstractions.Command;
import dialog.internalCommands.handleTextInput.AnswerInputCommand;
import dialog.internalCommands.handleTextInput.QuestionInputCommand;
import dialog.state.AddAnswerState;
import dialog.state.DialogState;
import dialog.state.ReadAnswerState;
import storage.CardStorage;

public class StateMachine {
    private final AddCardExecutor addCardExecutor;
    private final QuestionInputCommandExecutor questionInputCommandExecutor;
    private final AnswerInputCommandExecutor answerInputCommandExecutor;
    private final HelpCommandExecutor helpCommandExecutor;
    private final ReadCardExecutor readCardExecutor;
    private final ShowAnswerExecutor showAnswerExecutor;

    private DialogState state;

    public StateMachine(DialogState state, CardStorage cardStorage) {
        this.state = state;
        addCardExecutor = new AddCardExecutor();
        questionInputCommandExecutor = new QuestionInputCommandExecutor();
        helpCommandExecutor = new HelpCommandExecutor();
        showAnswerExecutor = new ShowAnswerExecutor();
        answerInputCommandExecutor = new AnswerInputCommandExecutor(cardStorage);
        readCardExecutor = new ReadCardExecutor(cardStorage);
    }

    public DialogResponse handleCommand(Command command) {

        if (command instanceof TextInputCommand textInputCommand) {
            if (this.state.handleInputCommand == null)
                return new DialogResponse("Неизвестная команда");

            command = this.state.handleInputCommand;

            return executeCommand(command, textInputCommand.text);
        }

        if (command.getSourceStep() != this.state.currentStep) {
            return new DialogResponse("Сейчас нельзя выполнить эту команду");
        }

        return executeCommand(command);
    }

    private DialogResponse executeCommand(Command command) {
        return executeCommand(command, null);
    }

    private DialogResponse executeCommand(Command command, String text) {
        var result = switch (command) {
            case AddCardCommand _ -> addCardExecutor.execute(state);
            case HelpCommand _ -> helpCommandExecutor.execute(state);
            case ReadCardCommand _ -> readCardExecutor.execute(state);
            case ShowAnswerCommand _ -> showAnswerExecutor.execute((ReadAnswerState) state);
            case AnswerInputCommand _ -> answerInputCommandExecutor.execute((AddAnswerState) state, text);
            case QuestionInputCommand _ -> questionInputCommandExecutor.execute(state, text);
            default -> throw new IllegalArgumentException("Unexpected command:" + command);
        };

        state = result.nextState();
        return new DialogResponse(result.message());
    }
}
