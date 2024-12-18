package dialog;

import dialog.commands.abstractions.Command;
import dialog.state.DialogState;
import dialog.state.DialogStep;
import dialog.user.User;
import storage.CardRatingStatisticsStorage;
import storage.CardStorage;

public class UserDialog {

    private final StateMachine stateMachine;

    public UserDialog(User user, CardStorage cardStorage, CardRatingStatisticsStorage cardRatingStatisticsStorage) {
        this.stateMachine = new StateMachine(
                new DialogState(user)
                        .with(DialogStep.MENU),
                cardStorage,
                cardRatingStatisticsStorage
        );
    }

    public DialogResponse handleCommand(Command command) {
        return stateMachine.handleCommand(command);
    }
}
