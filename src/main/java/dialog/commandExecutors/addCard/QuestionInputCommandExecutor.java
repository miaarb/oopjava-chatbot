package dialog.commandExecutors.addCard;

import dialog.commandExecutors.CommandExecutionResult;
import dialog.commandExecutors.CommandExecutorType;
import dialog.commandExecutors.abstractions.HandleTextCommandExecutor;
import dialog.internalCommands.handleTextInput.AnswerInputCommand;
import dialog.state.DialogState;
import dialog.state.DialogStep;

import java.util.Map;

public class QuestionInputCommandExecutor implements HandleTextCommandExecutor {
    public CommandExecutorType getType() {
        return CommandExecutorType.QUESTION_INPUT;
    }

    public CommandExecutionResult execute(DialogState state, String text) {

        return new CommandExecutionResult(
                "Введите ответ: ",
                new DialogState(state.user)
                        .with(DialogStep.ANSWER_INPUT)
                        .with(new AnswerInputCommand())
                        .with(Map.of("question", text)));
    }
}
