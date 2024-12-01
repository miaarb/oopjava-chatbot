package dialog.commandexecutors.addcard;

import dialog.commandexecutors.CommandExecutionResult;
import dialog.commandexecutors.abstractions.HandleTextCommandExecutor;
import dialog.state.AddAnswerState;
import dialog.state.DialogState;

public class QuestionInputCommandExecutor implements HandleTextCommandExecutor<DialogState> {

    public CommandExecutionResult execute(DialogState state, String text) {

        return new CommandExecutionResult(
                "Введите ответ: ",
                new AddAnswerState(state.user, text));
    }
}
