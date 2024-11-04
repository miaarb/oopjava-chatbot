package dialog;

import dialog.commands.abstractions.Command;
import dialog.state.DialogState;
import dialog.state.DialogStep;
import dialog.user.User;

public class UserDialog {

    private final StateMachine stateMachine;

    public UserDialog(User user) {
        this.stateMachine = new StateMachine(
                new DialogState(user)
                        .with(DialogStep.MENU));
    }

    public DialogResponse handleCommand(Command command) {
        return stateMachine.handleCommand(command);
    }
}
