package dialog.commandExecutors.addCard;

import dialog.commandExecutors.CommandExecutionResult;
import dialog.commandExecutors.CommandExecutorType;
import dialog.commandExecutors.abstractions.HandleTextCommandExecutor;
import dialog.state.AddAnswerState;
import dialog.state.DialogState;

public class QuestionInputCommandExecutor implements HandleTextCommandExecutor<DialogState> {
    public CommandExecutorType getType() {
        return CommandExecutorType.QUESTION_INPUT;
    }

    public CommandExecutionResult execute(DialogState state, String text) {

        return new CommandExecutionResult(
                "Введите ответ: ",
                new AddAnswerState(state.user, text));
    }
}
