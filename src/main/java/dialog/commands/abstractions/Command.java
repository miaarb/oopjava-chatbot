package dialog.commands.abstractions;

import dialog.commandExecutors.CommandExecutorType;
import dialog.state.DialogStep;

public interface Command {
    DialogStep getSourceStep();

    CommandExecutorType getExecutorType();
}
