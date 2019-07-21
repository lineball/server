package lineball.server.persistence.memory;

import lineball.server.domain.DomainException;
import lineball.server.domain.game.GameStatus;
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

    @Override
    public Game getActiveByFieldId(UUID fieldId) {
        return map.values().stream()
                .filter(g -> g.getFieldId().equals(fieldId) && g.getStatus().equals(GameStatus.ACTIVE))
                .findFirst()
                .orElseThrow(() -> new DomainException("s"));
    }
}
