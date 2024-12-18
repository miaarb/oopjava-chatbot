package dialog.commandexecutors.readcard;

import dialog.commandexecutors.CommandExecutionResult;
import dialog.commandexecutors.abstractions.HandleTextCommandExecutor;
import dialog.state.ActiveCardDialogState;
import dialog.state.DialogState;
import dialog.state.DialogStep;
import domain.cardratingstatistics.CardRating;
import storage.CardRatingStatisticsStorage;

public class RateCardCommandExecutor implements HandleTextCommandExecutor<ActiveCardDialogState> {
    private final CardRatingStatisticsStorage ratingStorage;

    public RateCardCommandExecutor(CardRatingStatisticsStorage cardRatingStatisticsStorage) {
        this.ratingStorage = cardRatingStatisticsStorage;
    }

    @Override
    public CommandExecutionResult execute(ActiveCardDialogState state, String text) {
        CardRating rating;
        switch (text) {
            case "0":
                rating = CardRating.AGAIN;
                break;
            case "1":
                rating = CardRating.HARD;
                break;
            case "2":
                rating = CardRating.GOOD;
                break;
            case "3":
                rating = CardRating.EASY;
                break;
            default:
                return new CommandExecutionResult(
                        "Введите корректное число от 0 до 3",
                        state
                );
        }

        ratingStorage.addRating(state.user.id(), state.card.id(), rating);
        var statistics = ratingStorage.getCardRatingStatistics(state.user.id(), state.card.id());

        var builder = new StringBuilder("Статистика оценок:\n");
        for (var rate : CardRating.values()) {
            builder.append(String.format("%s:%s; ", rate, statistics.getOrDefault(rate, 0)));
        }

        return new CommandExecutionResult(
                builder.toString(),
                new DialogState(state.user)
                        .with(DialogStep.MENU)
        );
    }
}
