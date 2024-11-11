package dialog;

import dialog.commands.abstractions.Command;
import dialog.state.DialogState;
import dialog.state.DialogStep;
import dialog.user.User;
import storage.CardStorage;

public class UserDialog {

    private final StateMachine stateMachine;

    public UserDialog(User user, CardStorage cardStorage) {
        this.stateMachine = new StateMachine(
                new DialogState(user)
                        .with(DialogStep.MENU),
                cardStorage);
    }

    public DialogResponse handleCommand(Command command) {
        return stateMachine.handleCommand(command);
    }
}
