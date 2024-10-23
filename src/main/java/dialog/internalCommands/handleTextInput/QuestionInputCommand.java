package dialog.internalCommands.handleTextInput;

import dialog.commandExecutors.CommandExecutorType;
import dialog.state.DialogStep;

public class QuestionInputCommand implements HandleTextCommand {

    public DialogStep getSourceState() {
        return DialogStep.QuestionInput;
    }

    public CommandExecutorType getExecutorType() {
        return CommandExecutorType.QuestionInput;
    }
}
