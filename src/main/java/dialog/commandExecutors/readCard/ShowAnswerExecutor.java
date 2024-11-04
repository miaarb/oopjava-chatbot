package dialog.commandExecutors.readCard;

import dialog.commandExecutors.CommandExecutionResult;
import dialog.commandExecutors.CommandExecutorType;
import dialog.commandExecutors.abstractions.CommandExecutor;
import dialog.state.DialogState;
import dialog.state.DialogStep;

public class ShowAnswerExecutor implements CommandExecutor {
    public CommandExecutorType getType() {
        return CommandExecutorType.SHOW_ANSWER;
    }

    public CommandExecutionResult execute(DialogState state) {
        var cardAnswer = state.stateArgs.get("answer");

        return new CommandExecutionResult(
                cardAnswer,
                new DialogState(state.user)
                        .with(DialogStep.MENU));
    }
}
