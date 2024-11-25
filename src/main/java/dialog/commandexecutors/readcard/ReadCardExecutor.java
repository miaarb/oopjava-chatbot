package dialog.commandexecutors.readcard;

import dialog.commandexecutors.CommandExecutionResult;
import dialog.commandexecutors.abstractions.CommandExecutor;
import dialog.state.DialogState;
import dialog.state.DialogStep;
import dialog.state.ReadAnswerState;
import storage.CardStorage;

public class ReadCardExecutor implements CommandExecutor<DialogState> {
    private final CardStorage cardStorage;

    public ReadCardExecutor(CardStorage cardStorage) {
        this.cardStorage = cardStorage;
    }

    public CommandExecutionResult execute(DialogState state) {
        var card = cardStorage.getRandom(state.user.id());

        if (card == null)
            return new CommandExecutionResult(
                    "У вас пока нет добавленных карт",
                    new DialogState(state.user)
                            .with(DialogStep.MENU));

        return new CommandExecutionResult(
                card.question(),
                new ReadAnswerState(state.user, card.answer()));
    }
}
