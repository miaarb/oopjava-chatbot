package Dialog;

import Domain.Card.Card;
import Domain.Command.Command;
import Domain.Command.CommandType;
import Storage.CardStorage;
import Storage.InMemoryCardStorage;

public class DialogManager {

    private final CardStorage cardStorage = new InMemoryCardStorage();

    private Card currentCard;
    private Card creatingCard;


    public String handleCommand(Command command) {
        switch (command.type()){
            case CommandType.ShowHelp:
                return """
                        Этот бот поможет вам запомнить любую информацию и не забывать её в будущем.
                        Для начала работы начните добавлять карточки в формате вопрос-ответ. Потом можно будет их просматривать и повторять
                        команды:
                        /help -- справка
                        /add -- добавить карточку вопрос-ответ
                        /read -- получить случайную карточку
                        /show -- показать ответ
                        """;

            case CommandType.AddCard:
                creatingCard = new Card(null, null);
                return "Введите вопрос: ";

            case CommandType.ReadCards:
                currentCard = cardStorage.getRandom();

                if(currentCard == null)
                    return "Сперва добавьте карту";

                return currentCard.question();

            case CommandType.ShowAnswer:
                if(currentCard == null)
                    return "Сперва откройте карту";

                var answer = currentCard.answer();
                currentCard = null;
                return answer;

            case CommandType.TextMessage:
                if(creatingCard == null)
                    return "Неизвестный ввод";

                if(creatingCard.question() == null) {
                    creatingCard = new Card(command.message(), null);
                    return "Введите ответ: ";
                }
                else {
                    creatingCard = new Card(creatingCard.question(), command.message());
                    cardStorage.add(creatingCard);
                    creatingCard = null;
                    return "Карта добавлена";
                }

            default:
                return "Неизвестная команда";
        }
    }
}
