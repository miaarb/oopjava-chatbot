package dialog.commandExecutors.addCard;

import dialog.commandExecutors.CommandExecutionResult;
import dialog.commandExecutors.CommandExecutorType;
import dialog.commandExecutors.abstractions.HandleTextCommandExecutor;
import dialog.state.AddAnswerState;
import dialog.state.DialogState;
import dialog.state.DialogStep;
import domain.card.Card;
import storage.CardStorage;

public class AnswerInputCommandExecutor implements HandleTextCommandExecutor<AddAnswerState> {

    private final CardStorage cardStorage;

    public AnswerInputCommandExecutor(CardStorage cardStorage) {
        this.cardStorage = cardStorage;
    }

    public CommandExecutionResult execute(AddAnswerState state, String text) {

        var card = new Card(state.question, text);
        cardStorage.add(state.user.id(), card);

        return new CommandExecutionResult(
                "Карта добавлена",
                new DialogState(state.user)
                        .with(DialogStep.MENU));
    }

    public CommandExecutorType getType() {
        return CommandExecutorType.ANSWER_INPUT;
    }
}
