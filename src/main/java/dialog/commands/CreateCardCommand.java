package dialog.commands;

import dialog.commandExecutors.CommandExecutorType;
import dialog.commands.abstractions.Command;
import dialog.state.DialogStep;

public class CreateCardCommand implements Command {

    public DialogStep getSourceStep() {
        return DialogStep.MENU;
    }

    public CommandExecutorType getExecutorType() {
        return CommandExecutorType.ADD_CARD;
    }
}
