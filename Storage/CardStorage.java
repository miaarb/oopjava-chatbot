package Storage;

import Domain.Card.Card;

public interface CardStorage {
    Card getRandom();

    void add(Card card);
}
