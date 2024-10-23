package dialog.commands.abstractions;

import dialog.commandExecutors.CommandExecutorType;
import dialog.state.DialogStep;

public interface Command {
    DialogStep getSourceState();

    CommandExecutorType getExecutorType();
}
