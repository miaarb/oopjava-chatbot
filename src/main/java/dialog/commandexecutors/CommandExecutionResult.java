package dialog.commandexecutors;

import dialog.state.DialogState;

public record CommandExecutionResult(String message, DialogState nextState) {
}
