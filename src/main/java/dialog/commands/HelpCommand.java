package dialog.commands;

import dialog.commandExecutors.CommandExecutorType;
import dialog.commands.abstractions.Command;
import dialog.state.DialogStep;

public class HelpCommand implements Command {
    public DialogStep getSourceStep() {
        return DialogStep.MENU;
    }

    public CommandExecutorType getExecutorType() {
        return CommandExecutorType.SHOW_HELP;
    }
}
