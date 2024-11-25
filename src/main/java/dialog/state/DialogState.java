package dialog.state;

import dialog.internalcommands.handletextinput.HandleTextCommand;
import dialog.user.User;

public sealed class DialogState permits AddAnswerState, ReadAnswerState {
    public final DialogStep currentStep;
    public final HandleTextCommand handleInputCommand;
    public final User user;

    public DialogState(User user) {
        this.user = user;
        this.currentStep = null;
        this.handleInputCommand = null;
    }

    protected DialogState(User user, DialogStep currentStep, HandleTextCommand handleInputCommand) {
        this.user = user;
        this.currentStep = currentStep;
        this.handleInputCommand = handleInputCommand;
    }

    public DialogState with(DialogStep newStep) {
        return new DialogState(this.user, newStep, this.handleInputCommand);
    }

    public DialogState with(HandleTextCommand handleInputCommand) {
        return new DialogState(this.user, this.currentStep, handleInputCommand);
    }
}
