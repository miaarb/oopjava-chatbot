package dialog.internalCommands.handleTextInput;

import dialog.state.DialogStep;


public class AnswerInputCommand implements HandleTextCommand {

    public DialogStep getSourceStep() {
        return DialogStep.ANSWER_INPUT;
    }

}
