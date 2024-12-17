package storage;

import domain.cardratingstatistics.CardRating;

public interface CardRatingStatisticsStorage {
    void addRating(Long userId, Long cardId, CardRating rating);
}
