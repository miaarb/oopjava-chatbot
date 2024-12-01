package dialog.state;

import dialog.internalcommands.handletextinput.AnswerInputCommand;
import dialog.user.User;

public final class AddAnswerState extends DialogState {
    public final String question;

    public AddAnswerState(User user, String question) {
        super(user, DialogStep.ANSWER_INPUT, new AnswerInputCommand());
        this.question = question;
    }
}
