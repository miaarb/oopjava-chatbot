package dialog.commands;

import dialog.commands.abstractions.Command;
import dialog.state.DialogStep;

public class ShowAnswerCommand implements Command {
    @Override
    public DialogStep getSourceStep() {
        return DialogStep.ANSWER_SHOW;
    }

}
