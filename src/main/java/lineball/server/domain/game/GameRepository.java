package lineball.server.domain.game;

import java.util.Optional;
import java.util.UUID;

public interface GameRepository {

    Optional<Game> getById(UUID id);

    void save(Game game);
}
