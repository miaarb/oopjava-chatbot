package dialog.commandExecutors.readCard;

import dialog.commandExecutors.CommandExecutionResult;
import dialog.commandExecutors.CommandExecutorType;
import dialog.commandExecutors.abstractions.CommandExecutor;
import dialog.state.DialogState;
import dialog.state.DialogStep;
import storage.CardStorage;

import java.util.Map;

public class ReadCardExecutor implements CommandExecutor {
    private final CardStorage cardStorage;

    public ReadCardExecutor(CardStorage cardStorage) {
        this.cardStorage = cardStorage;
    }

    public CommandExecutorType getType() {
        return CommandExecutorType.ReadCard;
    }

    public CommandExecutionResult execute(DialogState state) {
        var card = cardStorage.getRandom(state.user.id());
        return new CommandExecutionResult(
                card.question(),
                new DialogState(state.user)
                        .With(DialogStep.QuestionShow)
                        .With(Map.of("answer", card.answer())));
    }
}