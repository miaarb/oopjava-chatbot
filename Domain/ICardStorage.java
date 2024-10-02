package Domain;

public interface ICardStorage {
    Card[] getAll();

    Card getRandom();

    void add(Card card);
}
