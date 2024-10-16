package domain.command;

import dialog.CommandExecutionResult;
import dialog.State;
import dialog.StateEnum;

public class CreateCardCommand implements Command {
    public CommandExecutionResult execute(State state) {
        return new CommandExecutionResult("Введите вопрос: ",new State(StateEnum.WaitTextInput, null));
    }
}
