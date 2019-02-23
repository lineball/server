package lineball.server.persistence.memory;

import lineball.server.domain.game.Game;
import lineball.server.domain.game.GameRepository;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GameInMemoryRepository implements GameRepository {

    private final ConcurrentHashMap<UUID, Game> map = new ConcurrentHashMap<>();

    @Override
    public void save(Game game) {
        map.put(game.getId(), game);
    }
}
