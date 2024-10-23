package dialog.internalCommands.handleTextInput;

import dialog.commandExecutors.CommandExecutorType;
import dialog.state.DialogStep;


public class AnswerInputCommand implements HandleTextCommand {

    public DialogStep getSourceState() {
        return DialogStep.AnswerInput;
    }

    public CommandExecutorType getExecutorType() {
        return CommandExecutorType.AnswerInput;
    }
}
