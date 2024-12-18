package dialog.commandexecutors.addcard;

import dialog.commandexecutors.CommandExecutionResult;
import dialog.commandexecutors.abstractions.HandleTextCommandExecutor;
import dialog.state.AddAnswerState;
import dialog.state.DialogState;
import dialog.state.DialogStep;
import domain.card.Card;
import storage.CardStorage;

import java.util.Random;

public class AnswerInputCommandExecutor implements HandleTextCommandExecutor<AddAnswerState> {

    private final CardStorage cardStorage;

    public AnswerInputCommandExecutor(CardStorage cardStorage) {
        this.cardStorage = cardStorage;
    }

    public CommandExecutionResult execute(AddAnswerState state, String text) {

        var card = new Card(Math.abs(new Random().nextLong()), state.question, text);
        cardStorage.add(state.user.id(), card);

        return new CommandExecutionResult(
                "Карта добавлена",
                new DialogState(state.user)
                        .with(DialogStep.MENU));
    }
}
