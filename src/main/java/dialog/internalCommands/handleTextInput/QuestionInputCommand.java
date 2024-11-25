package dialog.internalCommands.handleTextInput;

import dialog.state.DialogStep;

public class QuestionInputCommand implements HandleTextCommand {

    public DialogStep getSourceStep() {
        return DialogStep.QUESTION_INPUT;
    }

}
