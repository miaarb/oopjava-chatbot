package dialog;

import java.util.Dictionary;

public record State(StateEnum currentState, Dictionary<String, String> stateArgs);
