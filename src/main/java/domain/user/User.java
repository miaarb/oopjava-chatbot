package domain.user;

import storage.CardStorage;
import storage.InMemoryCardStorage;

public class User {
    public final CardStorage CardStorage = new InMemoryCardStorage();
}
