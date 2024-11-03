package dialog.commandExecutors.readCard;

import dialog.commandExecutors.CommandExecutionResult;
import dialog.commandExecutors.CommandExecutorType;
import dialog.commandExecutors.abstractions.CommandExecutor;
import dialog.state.DialogState;
import dialog.state.DialogStep;

public class ShowAnswerExecutor implements CommandExecutor {
    public CommandExecutorType getType() {
        return CommandExecutorType.ShowAnswer;
    }

    public CommandExecutionResult execute(DialogState state) {
        var cardAnswer = state.stateArgs.get("answer");

        return new CommandExecutionResult(
                cardAnswer,
                new DialogState(state.user)
                        .With(DialogStep.Menu));
    }
}
