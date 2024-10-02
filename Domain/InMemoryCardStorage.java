package Domain;

import java.util.ArrayList;
import java.util.Random;

public class InMemoryCardStorage implements ICardStorage {
    private final ArrayList<Card> cards = new ArrayList<>();
    private final Random random = new Random();

    @Override
    public Card[] getAll() {
        return cards.toArray(new Card[0]);
    }

    @Override
    public Card getRandom() {
        return cards.get(random.nextInt(cards.size()));
    }

    @Override
    public void add(Card card) {
        cards.add(card);
    }
}
