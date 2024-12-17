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

    public static CardRating fromString(String rateString) {
        int rate;
        try {
            rate = Integer.parseInt(rateString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid rate: " + rateString);
        }

        for (CardRating rating : CardRating.values()) {
            if (rating.rate == rate) {
                return rating;
            }
        }

        throw new IllegalArgumentException("No matching CardRating for rate: " + rate);
    }
}
