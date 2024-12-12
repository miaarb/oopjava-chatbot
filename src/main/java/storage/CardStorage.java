package storage;

import domain.card.Card;

public interface CardStorage {
    Card getRandom(Long userId);

    void add(Long userId, Card card);
}
