package dialog.commands;

import dialog.commands.abstractions.Command;
import dialog.state.DialogStep;

public class HelpCommand implements Command {
    public DialogStep getSourceStep() {
        return DialogStep.MENU;
    }

}
