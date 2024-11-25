package dialog.commandExecutors.addCard;

import dialog.commandExecutors.CommandExecutionResult;
import dialog.commandExecutors.abstractions.CommandExecutor;
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
