package dialog.commandExecutors.readCard;

import dialog.commandExecutors.CommandExecutionResult;
import dialog.commandExecutors.CommandExecutorType;
import dialog.commandExecutors.abstractions.CommandExecutor;
import dialog.state.DialogState;
import dialog.state.DialogStep;
import dialog.state.ReadAnswerState;
import storage.CardStorage;

public class ReadCardExecutor implements CommandExecutor<DialogState> {
    private final CardStorage cardStorage;

    public ReadCardExecutor(CardStorage cardStorage) {
        this.cardStorage = cardStorage;
    }

    public CommandExecutorType getType() {
        return CommandExecutorType.READ_CARD;
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
