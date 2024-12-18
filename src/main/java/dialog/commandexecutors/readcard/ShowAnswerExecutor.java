package dialog.commandexecutors.readcard;

import dialog.commandexecutors.CommandExecutionResult;
import dialog.commandexecutors.abstractions.CommandExecutor;
import dialog.internalcommands.handletextinput.RateCardCommand;
import dialog.state.ActiveCardDialogState;
import dialog.state.DialogStep;

public class ShowAnswerExecutor implements CommandExecutor<ActiveCardDialogState> {

    public CommandExecutionResult execute(ActiveCardDialogState state) {
        var cardAnswer = state.card.answer();
        var rateMessage = """
                \n===================================================
                Оцените, как хорошо вы знали ответ (введите число):
                0: повторить заново,
                1: тяжело,
                2: хорошо,
                3: легко""";

        return new CommandExecutionResult(
                cardAnswer + rateMessage,
                new ActiveCardDialogState(state.user, DialogStep.RATE_CARD, new RateCardCommand(), state.card));
    }
}
