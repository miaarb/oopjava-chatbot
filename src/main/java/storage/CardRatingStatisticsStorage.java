package storage;

import domain.cardratingstatistics.CardRating;

import java.util.List;
import java.util.Map;

public interface CardRatingStatisticsStorage {
    void addRating(Long userId, Long cardId, CardRating rating);

    Map<CardRating, Integer> getCardRatingStatistics(Long userId, Long cardId);

    Map<CardRating, Integer> getTotalStatistics(Long userId);

    List<Long> getCardsWithWorstTotalRating(Long userId, int count);
}
