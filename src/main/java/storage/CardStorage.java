package storage;

import domain.card.Card;

public interface CardStorage {
    Card get(Long userId, Long cardId);

    Card getRandom(Long userId);

    void add(Long userId, Card card);
}
