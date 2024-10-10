package domain.command;

public record Command(CommandType type, String message) {
}
