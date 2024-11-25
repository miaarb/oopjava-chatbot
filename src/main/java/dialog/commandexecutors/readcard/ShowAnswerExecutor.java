package dialog.commandexecutors.readcard;

import dialog.commandexecutors.CommandExecutionResult;
import dialog.commandexecutors.abstractions.CommandExecutor;
import dialog.state.DialogState;
import dialog.state.DialogStep;
import dialog.state.ReadAnswerState;

public class ShowAnswerExecutor implements CommandExecutor<ReadAnswerState> {

    public CommandExecutionResult execute(ReadAnswerState state) {
        var cardAnswer = state.answer;

        return new CommandExecutionResult(
                cardAnswer,
                new DialogState(state.user)
                        .with(DialogStep.MENU));
    }
}
