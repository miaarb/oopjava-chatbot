package storage;

import domain.cardratingstatistics.CardRating;
import domain.cardratingstatistics.CardRatingStatistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryCardRatingStatisticsStorage implements CardRatingStatisticsStorage {
    private final Map<Long, Map<Long, CardRatingStatistics>> storage = new HashMap<>();

    public Map<CardRating, Integer> getTotalStatistics(Long userId) {
        var cardsStatistics = storage.getOrDefault(userId, new HashMap<>()).values();

        var cardRatings = cardsStatistics.stream()
                .flatMap(x -> x.getStatistics().entrySet().stream())
                .toList();

        var totalStatistics = new HashMap<CardRating, Integer>();

        for (var entry : cardRatings) {
            totalStatistics.put(entry.getKey(), totalStatistics.getOrDefault(entry.getKey(), 0) + entry.getValue());
        }

        return totalStatistics;
    }

    public Map<CardRating, Integer> getCardRatingStatistics(Long userId, Long cardId) {
        var cardsStatistics = storage.getOrDefault(userId, new HashMap<>());
        var statistics = cardsStatistics.getOrDefault(cardId, new CardRatingStatistics());

        return statistics.getStatistics();
    }

    public void addRating(Long userId, Long cardId, CardRating rating) {
        var cardsStatistics = storage.getOrDefault(userId, new HashMap<>());
        var statistics = cardsStatistics.getOrDefault(cardId, new CardRatingStatistics());

        statistics.addRating(rating);
        cardsStatistics.putIfAbsent(cardId, statistics);
        storage.putIfAbsent(userId, cardsStatistics);
    }

    public List<Long> getCardsWithWorstTotalRating(Long userId, int count) {
        var cardsStatistics = storage.getOrDefault(userId, new HashMap<>());
        if (cardsStatistics.isEmpty())
            return new ArrayList<>();

        return cardsStatistics.entrySet().stream()
                .map(x -> Map.entry(x.getKey(), x.getValue().getTotalRating()))
                .sorted(Map.Entry.comparingByValue())
                .limit(count)
                .map(Map.Entry::getKey)
                .toList();
    }
}
