package storage;

import domain.card.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class InMemoryCardStorage implements CardStorage {
    private final Map<Long, List<Card>> storage = new HashMap<>();
    private final Random random;

    public InMemoryCardStorage(Random random) {
        this.random = random;
    }

    public InMemoryCardStorage() {
        this(new Random());
    }

    public Card getRandom(Long userId) {
        var cards = storage.getOrDefault(userId, new ArrayList<>());
        if (cards.isEmpty()) {
            return null;
        }

        var index = random.nextInt(cards.size());

        return cards.get(index);
    }

    public void add(Long userId, Card card) {
        var cards = storage.getOrDefault(userId, new ArrayList<>());
        cards.add(card);
        storage.put(userId, cards);
    }
}
