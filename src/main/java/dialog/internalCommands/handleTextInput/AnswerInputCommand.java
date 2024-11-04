package dialog.internalCommands.handleTextInput;

import dialog.commandExecutors.CommandExecutorType;
import dialog.state.DialogStep;


public class AnswerInputCommand implements HandleTextCommand {

    public DialogStep getSourceStep() {
        return DialogStep.ANSWER_INPUT;
    }

    public CommandExecutorType getExecutorType() {
        return CommandExecutorType.ANSWER_INPUT;
    }
}
