package dialog;

import dialog.commandexecutors.addcard.AddCardExecutor;
import dialog.commandexecutors.addcard.AnswerInputCommandExecutor;
import dialog.commandexecutors.addcard.QuestionInputCommandExecutor;
import dialog.commandexecutors.readcard.RateCardCommandExecutor;
import dialog.commandexecutors.readcard.ReadCardExecutor;
import dialog.commandexecutors.readcard.ShowAnswerExecutor;
import dialog.commandexecutors.showhelp.HelpCommandExecutor;
import dialog.commands.AddCardCommand;
import dialog.commands.HelpCommand;
import dialog.commands.ReadCardCommand;
import dialog.commands.ShowAnswerCommand;
import dialog.commands.TextInputCommand;
import dialog.commands.abstractions.Command;
import dialog.internalcommands.handletextinput.AnswerInputCommand;
import dialog.internalcommands.handletextinput.QuestionInputCommand;
import dialog.internalcommands.handletextinput.RateCardCommand;
import dialog.state.AddAnswerState;
import dialog.state.DialogState;
import dialog.state.ActiveCardDialogState;
import storage.CardRatingStatisticsStorage;
import storage.CardStorage;

public class StateMachine {
    private final AddCardExecutor addCardExecutor;
    private final QuestionInputCommandExecutor questionInputCommandExecutor;
    private final AnswerInputCommandExecutor answerInputCommandExecutor;
    private final HelpCommandExecutor helpCommandExecutor;
    private final ReadCardExecutor readCardExecutor;
    private final ShowAnswerExecutor showAnswerExecutor;
    private final RateCardCommandExecutor rateCardExecutor;

    private DialogState state;

    public StateMachine(
            DialogState state,
            CardStorage cardStorage,
            CardRatingStatisticsStorage cardRatingStatisticsStorage
    ) {
        this.state = state;
        addCardExecutor = new AddCardExecutor();
        questionInputCommandExecutor = new QuestionInputCommandExecutor();
        helpCommandExecutor = new HelpCommandExecutor();
        showAnswerExecutor = new ShowAnswerExecutor();
        answerInputCommandExecutor = new AnswerInputCommandExecutor(cardStorage);
        readCardExecutor = new ReadCardExecutor(cardStorage);
        rateCardExecutor = new RateCardCommandExecutor(cardRatingStatisticsStorage);
    }

    public DialogResponse handleCommand(Command command) {

        if (command instanceof TextInputCommand textInputCommand) {
            if (this.state.handleInputCommand == null) {
                return new DialogResponse("Неизвестная команда");
            }

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
            case ShowAnswerCommand _ -> showAnswerExecutor.execute((ActiveCardDialogState) state);
            case AnswerInputCommand _ -> answerInputCommandExecutor.execute((AddAnswerState) state, text);
            case QuestionInputCommand _ -> questionInputCommandExecutor.execute(state, text);
            case RateCardCommand _ -> rateCardExecutor.execute((ActiveCardDialogState) state, text);
            default -> throw new IllegalArgumentException("Unexpected command:" + command);
        };

        state = result.nextState();
        return new DialogResponse(result.message());
    }
}
