package dialog.internalcommands.handletextinput;

import dialog.state.DialogStep;

public class RateCardCommand implements HandleTextCommand{
    @Override
    public DialogStep getSourceStep() {
        return DialogStep.RATE_CARD;
    }
}
