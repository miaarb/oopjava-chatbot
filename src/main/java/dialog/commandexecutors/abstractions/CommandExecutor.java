package dialog.commandexecutors.abstractions;

import dialog.commandexecutors.CommandExecutionResult;
import dialog.state.DialogState;

public interface CommandExecutor<T extends DialogState> {
    CommandExecutionResult execute(T state);
}
