package storage;

import domain.cardratingstatistics.CardRating;
import domain.cardratingstatistics.CardRatingStatistics;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCardRatingStatisticsStorage {
    private final Map<Long, Map<Long, CardRatingStatistics>> storage = new HashMap<>();

    public Map<CardRating, Integer> getCardRatingStatics(Long userId, Long cardId) {
        var cardsStatistics = storage.getOrDefault(userId, new HashMap<>());
        var statistics = cardsStatistics.getOrDefault(cardId, new CardRatingStatistics());
        return statistics.getStatistics();
    }

    public void addRating(Long userId, Long cardId, CardRating rating) {
        var cardsStatistics = storage.getOrDefault(userId, new HashMap<>());
        var statistics = cardsStatistics.getOrDefault(cardId, new CardRatingStatistics());
        statistics.addRating(rating);
    }

    public Long[] getCardsWithWorstTotalRating(Long userId, int count) {
        var cardsStatistics = storage.getOrDefault(userId, new HashMap<>());

        if(cardsStatistics.isEmpty())
            return new Long[]{};

        
    }
}
