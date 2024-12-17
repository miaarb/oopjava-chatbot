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
        try {
            rating = CardRating.fromString(text);
        }
        catch (IllegalArgumentException exception) {
            return new CommandExecutionResult(
                    exception.getMessage() + "\nВведите корректное число",
                    new ActiveCardDialogState(state.user, state.card)
                            .with(DialogStep.RATE_CARD)
            );
        }

        ratingStorage.addRating(state.user.id(), state.card.id(), rating);
        return new CommandExecutionResult(
                "Спасибо, запомнили!",
                new DialogState(state.user)
                        .with(DialogStep.MENU)
        );
    }
}
