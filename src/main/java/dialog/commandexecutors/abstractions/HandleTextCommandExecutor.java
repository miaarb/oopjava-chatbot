package dialog.commandexecutors.abstractions;

import dialog.commandexecutors.CommandExecutionResult;
import dialog.state.DialogState;

public interface HandleTextCommandExecutor<T extends DialogState> {
    CommandExecutionResult execute(T state, String text);
}
