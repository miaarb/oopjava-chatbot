package dialog.commandExecutors.abstractions;

import dialog.commandExecutors.CommandExecutionResult;
import dialog.state.DialogState;

public interface CommandExecutor<T extends DialogState> {
    CommandExecutionResult execute(T state);
}
