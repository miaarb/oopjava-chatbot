package Domain;

public class DialogManager {

    private final ICardStorage cardStorage = new InMemoryCardStorage();

    private Card currentCard;
    private boolean isAddingCard;

    public String handleCommand(String command, String[] args) {
        switch (command){
            case "/help":
                return """
                        Этот бот поможет вам запомнить любую информацию и не забывать её в будущем.
                        Для начала работы начните добавлять карточки в формате вопрос-ответ. Потом можно будет их просматривать и повторять""";

            case "/add":
                cardStorage.add(new Card(args[0], args[1]));
                return "Карта добавлена";

            case "/read":
                currentCard = cardStorage.getRandom();
                return currentCard.question();

            case "/show":
                if(currentCard == null)
                    return "Сперва откройте карту";

                var answer = currentCard.answer();
                currentCard = null;
                return answer;

            default:
                return "Неизвестная команда";
        }
    }

    public String handleText() {
        if(!isAddingCard)
            return ""
    }
}
