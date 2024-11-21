package dialog.commandExecutors.abstractions;

import dialog.commandExecutors.CommandExecutionResult;

public interface HandleTextCommandExecutor<T> extends CommandExecutorBase {
    CommandExecutionResult execute(T state, String text);
}
