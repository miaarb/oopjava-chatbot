package storage;

import domain.card.Card;

import java.util.UUID;

public interface CardStorage {
    Card getRandom(UUID userId);

    void add(UUID userId, Card card);

    void clear();
}
