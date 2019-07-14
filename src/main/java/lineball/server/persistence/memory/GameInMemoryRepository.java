package lineball.server.persistence.memory;

import lineball.server.domain.Player;
import lineball.server.domain.game.dot.Dot;
import lineball.server.domain.game.dot.Coordinate;
import lineball.server.domain.field.Field;
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
    public Game getByPlayerId(UUID player) {
        return map.values().stream()
                .filter(game -> game.getWhite().getId().equals(player) || game.getBlack().getId().equals(player))
                .findFirst().orElseGet(this::newGame);
    }

    private Game newGame() {
        Player player1 = new Player(UUID.fromString("1bc9d166-83a7-4960-b387-069e6ecac1e7"));
        Player player2 = new Player(UUID.fromString("85777b10-da4a-42da-82c0-a6edc723f540"));
        Field field = new Field(UUID.randomUUID());
        field.enter(player1);
        field.enter(player2);
        Dot dot = new Dot(new Coordinate(0, 0), 8);
        return new Game(field, dot);
    }
}
