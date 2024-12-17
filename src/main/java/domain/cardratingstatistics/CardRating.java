package domain.cardratingstatistics;

public enum CardRating {
    AGAIN(-2),
    HARD (-1),
    GOOD(1),
    EASY(2);

    public final int rate;

    CardRating(int rate) {
        this.rate = rate;
    }
}
