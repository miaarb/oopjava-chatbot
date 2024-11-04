package dialog.commands;

import dialog.commandExecutors.CommandExecutorType;
import dialog.commands.abstractions.Command;
import dialog.state.DialogStep;

public class TextInputCommand implements Command {

    public final String text;

    public TextInputCommand(String text) {
        this.text = text;
    }

    public DialogStep getSourceStep() {
        return null;
    }

    public CommandExecutorType getExecutorType() {
        return null;
    }
}
