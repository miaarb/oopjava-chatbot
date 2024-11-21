package dialog.commands;

import dialog.commandExecutors.CommandExecutorType;
import dialog.commands.abstractions.Command;
import dialog.state.DialogStep;

public class ShowAnswerCommand implements Command {
    @Override
    public DialogStep getSourceStep() {
        return DialogStep.ANSWER_SHOW;
    }

    @Override
    public CommandExecutorType getExecutorType() {
        return CommandExecutorType.SHOW_ANSWER;
    }
}
