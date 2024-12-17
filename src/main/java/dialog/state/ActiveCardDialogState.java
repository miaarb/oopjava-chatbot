package dialog.state;

import dialog.user.User;
import domain.card.Card;

public final class ActiveCardDialogState extends DialogState {
    public final Card card;

    public ActiveCardDialogState(User user, Card card) {
        super(user);
        this.card = card;
    }
}
