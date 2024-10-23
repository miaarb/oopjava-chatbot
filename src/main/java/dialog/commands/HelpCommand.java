package dialog.commands;

import dialog.commandExecutors.CommandExecutorType;
import dialog.commands.abstractions.Command;
import dialog.state.DialogStep;

public class HelpCommand implements Command {
    public DialogStep getSourceState() {
        return DialogStep.Menu;
    }

    public CommandExecutorType getExecutorType() {
        return CommandExecutorType.ShowHelp;
    }
}
