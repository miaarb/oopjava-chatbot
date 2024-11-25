package dialog.commandexecutors.addcard;

import dialog.commandexecutors.CommandExecutionResult;
import dialog.commandexecutors.abstractions.CommandExecutor;
import dialog.internalcommands.handletextinput.QuestionInputCommand;
import dialog.state.DialogState;
import dialog.state.DialogStep;

public class AddCardExecutor implements CommandExecutor<DialogState> {

    public CommandExecutionResult execute(DialogState state) {
        return new CommandExecutionResult(
                "Введите вопрос: ",
                new DialogState(state.user)
                        .with(DialogStep.QUESTION_INPUT)
                        .with(new QuestionInputCommand()));
    }
}
