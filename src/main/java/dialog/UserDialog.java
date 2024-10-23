package dialog;

import dialog.commandExecutors.CommandExecutorType;
import dialog.commandExecutors.abstractions.CommandExecutor;
import dialog.commandExecutors.abstractions.CommandExecutorBase;
import dialog.commandExecutors.abstractions.HandleTextCommandExecutor;
import dialog.commandExecutors.addCard.AnswerInputCommandExecutor;
import dialog.commandExecutors.addCard.CreateCardExecutor;
import dialog.commandExecutors.addCard.QuestionInputCommandExecutor;
import dialog.commandExecutors.showHelp.HelpCommandExecutor;
import dialog.commands.TextInputCommand;
import dialog.commands.abstractions.Command;
import dialog.state.DialogState;
import dialog.state.DialogStep;
import dialog.user.User;
import storage.InMemoryCardStorage;

import java.util.HashMap;
import java.util.Map;

public class UserDialog {
    private static final Map<CommandExecutorType, CommandExecutorBase> commandExecutors = new HashMap<>(Map.of(
            CommandExecutorType.AddCard, new CreateCardExecutor(),
            CommandExecutorType.QuestionInput, new QuestionInputCommandExecutor(),
            CommandExecutorType.AnswerInput, new AnswerInputCommandExecutor(new InMemoryCardStorage()),
            CommandExecutorType.ShowHelp, new HelpCommandExecutor()
    ));
    private DialogState state;

    public UserDialog(User user) {
        this.state = new DialogState(user)
                .With(DialogStep.Menu);
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

        if (command.getSourceState() != this.state.currentStep) {
            return new DialogResponse("Сейчас нельзя выполнить эту команду");
        }

        var executor = commandExecutors.get(command.getExecutorType());

        var result = ((CommandExecutor) executor).execute(this.state);
        this.state = result.nextState();
        return new DialogResponse(result.message());
    }
}
