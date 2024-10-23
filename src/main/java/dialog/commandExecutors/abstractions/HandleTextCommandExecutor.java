package dialog.commandExecutors.abstractions;

import dialog.commandExecutors.CommandExecutionResult;
import dialog.state.DialogState;

public interface HandleTextCommandExecutor extends CommandExecutorBase {
    CommandExecutionResult execute(DialogState state, String text);
}
