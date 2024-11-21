package dialog.state;

import dialog.internalCommands.handleTextInput.AnswerInputCommand;
import dialog.user.User;

public final class ReadAnswerState extends DialogState {
    public final String answer;

    public ReadAnswerState(User user, String answer) {
        super(user, DialogStep.ANSWER_SHOW, new AnswerInputCommand());
        this.answer = answer;
    }
}
