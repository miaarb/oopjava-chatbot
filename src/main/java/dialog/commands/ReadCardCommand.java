package dialog.commands;

import dialog.commandExecutors.CommandExecutorType;
import dialog.commands.abstractions.Command;
import dialog.state.DialogStep;

public class ReadCardCommand implements Command {
    @Override
    public DialogStep getSourceStep() {
        return DialogStep.MENU;
    }

    @Override
    public CommandExecutorType getExecutorType() {
        return CommandExecutorType.READ_CARD;
    }
}
