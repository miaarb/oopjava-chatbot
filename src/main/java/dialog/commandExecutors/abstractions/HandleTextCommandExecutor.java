package dialog.commandExecutors.abstractions;

import dialog.commandExecutors.CommandExecutionResult;
import dialog.state.DialogState;

public interface HandleTextCommandExecutor<T extends DialogState> {
    CommandExecutionResult execute(T state, String text);
}
