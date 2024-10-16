package domain.command;

//public interface ICommand {
//    stateFrom
//    stateTo
//}

import dialog.CommandExecutionResult;
import dialog.State;

public interface Command {
    CommandExecutionResult execute(State state);
}
