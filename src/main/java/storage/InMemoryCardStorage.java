package storage;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InMemoryCardStorage implements CardStorage {
    private final List<Card> cards = new ArrayList<>();
    private final Random random;

    public InMemoryCardStorage(Random random) {
        this.random = random;
    }

    public InMemoryCardStorage() {
        this(new Random());
    }

    @Override
    public Card getRandom() {
        if(cards.isEmpty())
            return null;

        return cards.get(random.nextInt(cards.size()));
    }

    @Override
    public void add(Card card) {
        cards.add(card);
    }
}
