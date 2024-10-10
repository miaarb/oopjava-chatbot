package dialog;

import domain.command.Command;
import domain.command.CommandType;
import domain.user.User;

public class DialogManager {
    private UserDialog currentUserDialog;

    public DialogManager() {
        var user = new User();
        this.currentUserDialog = new UserDialog(user);
    }

    public ExecutionResult handleCommand(Command command) {
        currentUserDialog.resetCreatingCardIfNecessary(command);

        switch (command.type()){
            case CommandType.SHOW_HELP:
                return new ExecutionResult("""
                        Этот бот поможет вам запомнить любую информацию и не забывать её в будущем.
                        Для начала работы начните добавлять карточки в формате вопрос-ответ. Потом можно будет их просматривать и повторять
                        команды:
                        /help -- справка
                        /add -- добавить карточку вопрос-ответ
                        /read -- получить случайную карточку
                        /show -- показать ответ""");
            default:
                return currentUserDialog.handleCommand(command);
        }
    }
}
