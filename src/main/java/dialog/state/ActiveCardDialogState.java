package dialog.state;

import dialog.internalcommands.handletextinput.HandleTextCommand;
import dialog.user.User;
import domain.card.Card;

public final class ActiveCardDialogState extends DialogState {
    public final Card card;

    public ActiveCardDialogState(User user, DialogStep dialogStep, HandleTextCommand handleInputCommand, Card card) {
        super(user, dialogStep, handleInputCommand);
        this.card = card;
    }
}
