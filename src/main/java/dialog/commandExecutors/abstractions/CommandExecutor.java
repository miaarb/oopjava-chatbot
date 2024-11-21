package dialog.commandExecutors.abstractions;

import dialog.commandExecutors.CommandExecutionResult;

public interface CommandExecutor<T> extends CommandExecutorBase {
    CommandExecutionResult execute(T state);
}
