package Domain;

public interface CardStorage {
    Card getRandom();

    void add(Card card);
}
