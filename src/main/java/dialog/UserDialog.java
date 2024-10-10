package dialog;

import domain.card.Card;
import domain.command.Command;
import domain.command.CommandType;
import domain.user.User;

public class UserDialog {
    private final User user;
    private Card currentCard;
    private Card creatingCard;

    public UserDialog(User user) {
        this.user = user;
    }

    public ExecutionResult handleCommand(Command command) {
        switch (command.type()){
            case CommandType.ADD_CARD:
                creatingCard = new Card(null, null);
                return new ExecutionResult("Введите вопрос: ");

            case CommandType.READ_CARD:
                currentCard = user.cardStorage.getRandom();

                if(currentCard == null)
                    return new ExecutionResult("Сперва добавьте карту");

                return new ExecutionResult(currentCard.question());

            case CommandType.SHOW_ANSWER:
                if(currentCard == null)
                    return new ExecutionResult("Сперва откройте карту");

                var answer = currentCard.answer();
                currentCard = null;
                return new ExecutionResult(answer);

            case CommandType.TEXT_MESSAGE:
                if(creatingCard == null)
                    return new ExecutionResult("Неизвестная команда");

                if(creatingCard.question() == null) {
                    creatingCard = new Card(command.message(), null);
                    return new ExecutionResult("Введите ответ: ");
                }
                else {
                    creatingCard = new Card(creatingCard.question(), command.message());
                    user.cardStorage.add(creatingCard);
                    creatingCard = null;
                    return new ExecutionResult("Карта добавлена");
                }

            default:
                return new ExecutionResult("Неизвестная команда");
        }
    }

    public void resetCreatingCardIfNecessary(Command command) {
        if(creatingCard != null && command.type() != CommandType.TEXT_MESSAGE) {
            creatingCard = null;
        }
    }
}
