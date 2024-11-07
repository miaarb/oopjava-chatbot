package dialog;

import dialog.commandExecutors.CommandExecutorType;
import dialog.commandExecutors.abstractions.CommandExecutor;
import dialog.commandExecutors.abstractions.CommandExecutorBase;
import dialog.commandExecutors.abstractions.HandleTextCommandExecutor;
import dialog.commandExecutors.addCard.AnswerInputCommandExecutor;
import dialog.commandExecutors.addCard.CreateCardExecutor;
import dialog.commandExecutors.addCard.QuestionInputCommandExecutor;
import dialog.commandExecutors.readCard.ReadCardExecutor;
import dialog.commandExecutors.readCard.ShowAnswerExecutor;
import dialog.commandExecutors.showHelp.HelpCommandExecutor;
import dialog.commands.TextInputCommand;
import dialog.commands.abstractions.Command;
import dialog.state.DialogState;
import storage.CardStorage;
import storage.InMemoryCardStorage;

import java.util.Map;

public class StateMachine {
    public static final CardStorage cardStorage;
    private static final Map<CommandExecutorType, CommandExecutorBase> commandExecutors;

    static {
        cardStorage = new InMemoryCardStorage();
        commandExecutors = Map.of(
                CommandExecutorType.ADD_CARD, new CreateCardExecutor(),
                CommandExecutorType.QUESTION_INPUT, new QuestionInputCommandExecutor(),
                CommandExecutorType.ANSWER_INPUT, new AnswerInputCommandExecutor(cardStorage),
                CommandExecutorType.SHOW_HELP, new HelpCommandExecutor(),
                CommandExecutorType.READ_CARD, new ReadCardExecutor(cardStorage),
                CommandExecutorType.SHOW_ANSWER, new ShowAnswerExecutor()
        );
    }

    private DialogState state;


    public StateMachine(DialogState state) {
        this.state = state;
    }

    public DialogResponse handleCommand(Command command) {

        if (command instanceof TextInputCommand textInputCommand) {
            if (this.state.handleInputCommand == null)
                return new DialogResponse("Неизвестная команда");

            var executor = commandExecutors.get(this.state.handleInputCommand.getExecutorType());

            var result = ((HandleTextCommandExecutor) executor).execute(this.state, textInputCommand.text);
            this.state = result.nextState();
            return new DialogResponse(result.message());
        }

        if (command.getSourceStep() != this.state.currentStep) {
            return new DialogResponse("Сейчас нельзя выполнить эту команду");
        }

        var executor = commandExecutors.get(command.getExecutorType());

        var result = ((CommandExecutor) executor).execute(this.state);
        this.state = result.nextState();
        return new DialogResponse(result.message());
    }
}
