package dialog.state;

import com.google.common.collect.ImmutableMap;
import dialog.internalCommands.handleTextInput.HandleTextCommand;
import dialog.user.User;

import java.util.Map;

public class DialogState {
    public final DialogStep currentStep;
    public final HandleTextCommand handleInputCommand;
    public final ImmutableMap<String, String> stateArgs;
    public final User user;

    public DialogState(User user) {
        this.user = user;
        this.currentStep = null;
        this.handleInputCommand = null;
        this.stateArgs = null;
    }

    private DialogState(User user, DialogStep currentStep, HandleTextCommand handleInputCommand, Map<String, String> stateArgs) {
        this.user = user;
        this.currentStep = currentStep;
        this.handleInputCommand = handleInputCommand;
        this.stateArgs = stateArgs != null
                ? ImmutableMap.copyOf(stateArgs)
                : null;
    }

    public DialogState with(DialogStep newStep) {
        return new DialogState(this.user, newStep, this.handleInputCommand, this.stateArgs);
    }

    public DialogState with(HandleTextCommand handleInputCommand) {
        return new DialogState(this.user, this.currentStep, handleInputCommand, this.stateArgs);
    }

    public DialogState with(Map<String, String> stateArgs) {
        return new DialogState(this.user, this.currentStep, this.handleInputCommand, stateArgs);
    }
}
