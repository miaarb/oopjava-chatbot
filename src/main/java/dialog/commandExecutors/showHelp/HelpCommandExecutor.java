package dialog.commandExecutors.showHelp;

import dialog.commandExecutors.CommandExecutionResult;
import dialog.commandExecutors.CommandExecutorType;
import dialog.commandExecutors.abstractions.CommandExecutor;
import dialog.state.DialogState;
import dialog.state.DialogStep;

public class HelpCommandExecutor implements CommandExecutor {
    private static final String helpMessage = """
            Этот бот поможет вам запомнить любую информацию и не забывать её в будущем.
            Для начала работы начните добавлять карточки в формате вопрос-ответ. Потом можно будет их просматривать и повторять
            команды:
            /help -- справка
            /add -- добавить карточку вопрос-ответ
            /read -- получить случайную карточку
            /show -- показать ответ""";

    public CommandExecutionResult execute(DialogState state) {
        return new CommandExecutionResult(
                helpMessage,
                new DialogState(state.user)
                        .With(DialogStep.Menu));
    }

    public CommandExecutorType getType() {
        return CommandExecutorType.ShowHelp;
    }
}
