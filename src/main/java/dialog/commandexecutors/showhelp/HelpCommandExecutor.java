package dialog.commandexecutors.showhelp;

import dialog.commandexecutors.CommandExecutionResult;
import dialog.commandexecutors.abstractions.CommandExecutor;
import dialog.state.DialogState;
import dialog.state.DialogStep;

public class HelpCommandExecutor implements CommandExecutor<DialogState> {
    private static final String helpMessage = """
            Этот бот поможет вам запомнить любую информацию и не забывать её в будущем.
            Для начала работы начните добавлять карточки в формате вопрос-ответ.
            Потом можно будет их просматривать и повторять.
            Команды:
            /help -- справка
            /add -- добавить карточку вопрос-ответ
            /read -- получить случайную карточку
            /show -- показать ответ
            /stats -- посмотреть статистику ответов""";

    public CommandExecutionResult execute(DialogState state) {
        return new CommandExecutionResult(
                helpMessage,
                new DialogState(state.user)
                        .with(DialogStep.MENU));
    }
}
