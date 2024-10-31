package dialog.commands;

import dialog.commandExecutors.CommandExecutorType;
import dialog.commands.abstractions.Command;
import dialog.state.DialogStep;

public class ReadCardCommand implements Command {
    @Override
    public DialogStep getSourceState() {
        return DialogStep.Menu;
    }

    @Override
    public CommandExecutorType getExecutorType() {
        return CommandExecutorType.ReadCard;
    }
}
