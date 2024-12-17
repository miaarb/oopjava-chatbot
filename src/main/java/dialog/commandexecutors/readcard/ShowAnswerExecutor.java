package dialog.commandexecutors.readcard;

import dialog.commandexecutors.CommandExecutionResult;
import dialog.commandexecutors.abstractions.CommandExecutor;
import dialog.state.DialogStep;
import dialog.state.ActiveCardDialogState;

public class ShowAnswerExecutor implements CommandExecutor<ActiveCardDialogState> {

    public CommandExecutionResult execute(ActiveCardDialogState state) {
        var cardAnswer = state.card.answer();
        var rateMessage = """
                Оцените, как хорошо вы знали ответ (введите цифру):
                
                AGAIN(-2),
                HARD (-1),
                GOOD(1),
                EASY(2)
                """;

        return new CommandExecutionResult(
                cardAnswer + rateMessage,
                new ActiveCardDialogState(state.user, state.card)
                        .with(DialogStep.RATE_CARD));
    }
}
