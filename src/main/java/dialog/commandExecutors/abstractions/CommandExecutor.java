package dialog.commandExecutors.abstractions;

import dialog.commandExecutors.CommandExecutionResult;
import dialog.state.DialogState;

public interface CommandExecutor extends CommandExecutorBase {
    CommandExecutionResult execute(DialogState state);
}
