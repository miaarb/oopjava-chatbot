package Storage;

import Domain.Card.Card;

import java.util.ArrayList;
import java.util.Random;

public class InMemoryCardStorage implements CardStorage {
    private final ArrayList<Card> cards = new ArrayList<>();
    private final Random random = new Random();

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
