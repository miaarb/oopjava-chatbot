package dialog.commands.abstractions;

import dialog.state.DialogStep;

public interface Command {
    DialogStep getSourceStep();
}
