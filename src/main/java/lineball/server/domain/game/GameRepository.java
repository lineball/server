package lineball.server.domain.game;

import java.util.UUID;

public interface GameRepository {

    void save(Game game);

    Game getActiveByFieldId(UUID fieldId);
}
