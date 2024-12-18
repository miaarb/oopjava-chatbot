package dialog.commandexecutors.statistics;

import dialog.commandexecutors.CommandExecutionResult;
import dialog.commandexecutors.abstractions.CommandExecutor;
import dialog.state.DialogState;
import dialog.state.DialogStep;
import domain.cardratingstatistics.CardRating;
import storage.CardRatingStatisticsStorage;
import storage.CardStorage;

public class StatisticsCommandExecutor implements CommandExecutor<DialogState> {
    private final CardRatingStatisticsStorage ratingStorage;
    private final CardStorage cardStorage;


    public StatisticsCommandExecutor(CardRatingStatisticsStorage cardRatingStatisticsStorage, CardStorage cardStorage) {
        this.ratingStorage = cardRatingStatisticsStorage;
        this.cardStorage = cardStorage;
    }

    @Override
    public CommandExecutionResult execute(DialogState state) {
        var totalStatistics = ratingStorage.getTotalStatistics(state.user.id());
        var builder = new StringBuilder("Статистика по всем картам:\n");

        for (var rate : CardRating.values()) {
            builder.append(String.format("%s:%s; ", rate, totalStatistics.getOrDefault(rate, 0)));
        }

        builder.append("\nТоп 5 карт с худшей оценкой:");

        var cardsWithWorstRatingIds = ratingStorage.getCardsWithWorstTotalRating(state.user.id(), 5);
        var cards = cardsWithWorstRatingIds.stream().map(x -> cardStorage.get(state.user.id(), x)).toList();

        var index = 1;
        for (var card : cards) {
            builder.append(String.format("\n%s. %s;", index, card.question()));
            index++;
        }

        return new CommandExecutionResult(
                builder.toString(),
                new DialogState(state.user)
                        .with(DialogStep.MENU));
    }
}
