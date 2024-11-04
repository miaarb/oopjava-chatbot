package dialog.internalCommands.handleTextInput;

import dialog.commandExecutors.CommandExecutorType;
import dialog.state.DialogStep;

public class QuestionInputCommand implements HandleTextCommand {

    public DialogStep getSourceStep() {
        return DialogStep.QUESTION_INPUT;
    }

    public CommandExecutorType getExecutorType() {
        return CommandExecutorType.QUESTION_INPUT;
    }
}
