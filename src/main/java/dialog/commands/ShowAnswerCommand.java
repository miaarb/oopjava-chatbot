package dialog.commands;

import dialog.commandExecutors.CommandExecutorType;
import dialog.commands.abstractions.Command;
import dialog.state.DialogStep;

public class ShowAnswerCommand implements Command {
    @Override
    public DialogStep getSourceState() {
        return DialogStep.QuestionShow;
    }

    @Override
    public CommandExecutorType getExecutorType() {
        return CommandExecutorType.ShowAnswer;
    }
}
