package dialog.commands;

import dialog.commands.abstractions.Command;
import dialog.state.DialogStep;

public class StatisticsCommand implements Command {
    @Override
    public DialogStep getSourceStep() {
        return DialogStep.MENU;
    }
}
