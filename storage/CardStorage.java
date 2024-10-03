package storage;

import domain.card.Card;

public interface CardStorage {
    Card getRandom();

    void add(Card card);
}
