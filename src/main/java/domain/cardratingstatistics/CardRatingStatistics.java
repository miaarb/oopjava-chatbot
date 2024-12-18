package domain.cardratingstatistics;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CardRatingStatistics {
    public final Map<CardRating, Integer> statistics;

    public CardRatingStatistics(Map<CardRating, Integer> statistics) {
        this.statistics = statistics;
    }

    public CardRatingStatistics() {
        this(new HashMap<>());
    }

    public Map<CardRating, Integer> getStatistics() {
        return Collections.unmodifiableMap(statistics);
    }

    public int getTotalRating() {
        var total = 0;
        for (var entry : statistics.entrySet()) {
            total += entry.getKey().rate * entry.getValue();
        }
        return total;
    }

    public void addRating(CardRating rating) {
        if (!statistics.containsKey(rating)) {
            statistics.put(rating, 0);
        }
        statistics.put(rating, statistics.get(rating) + 1);
    }
}
