package dialog.commandExecutors.addCard;

import dialog.commandExecutors.CommandExecutionResult;
import dialog.commandExecutors.CommandExecutorType;
import dialog.commandExecutors.abstractions.CommandExecutor;
import dialog.internalCommands.handleTextInput.QuestionInputCommand;
import dialog.state.DialogState;
import dialog.state.DialogStep;

public class CreateCardExecutor implements CommandExecutor {

    public CommandExecutorType getType() {
        return CommandExecutorType.AddCard;
    }

    public CommandExecutionResult execute(DialogState state) {
        return new CommandExecutionResult(
                "Введите вопрос: ",
                new DialogState(state.user)
                        .With(DialogStep.QuestionInput)
                        .With(new QuestionInputCommand()));
    }
}
