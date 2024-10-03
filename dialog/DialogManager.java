package dialog;

import domain.card.Card;
import domain.command.Command;
import domain.command.CommandType;
import storage.CardStorage;
import storage.InMemoryCardStorage;

public class DialogManager {

    private final CardStorage cardStorage = new InMemoryCardStorage();

    private Card currentCard;
    private Card creatingCard;


    public ExecutionResult handleCommand(Command command) {
        if(creatingCard != null && command.type() != CommandType.TEXT_MESSAGE) {
            creatingCard = null;
        }

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

            case CommandType.ADD_CARD:
                creatingCard = new Card(null, null);
                return new ExecutionResult("Введите вопрос: ");

            case CommandType.READ_CARD:
                currentCard = cardStorage.getRandom();

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
                    return new ExecutionResult("Неизвестный ввод");

                if(creatingCard.question() == null) {
                    creatingCard = new Card(command.message(), null);
                    return new ExecutionResult("Введите ответ: ");
                }
                else {
                    creatingCard = new Card(creatingCard.question(), command.message());
                    cardStorage.add(creatingCard);
                    creatingCard = null;
                    return new ExecutionResult("Карта добавлена");
                }

            default:
                return new ExecutionResult("Неизвестная команда");
        }
    }
}
