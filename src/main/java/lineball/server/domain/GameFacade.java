package lineball.server.domain;

import lineball.server.domain.field.Field;
import lineball.server.domain.field.FieldRepository;
import lineball.server.domain.game.Game;
import lineball.server.domain.game.GameRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class GameFacade {

    private final FieldRepository fieldRepository;
    private final GameRepository gameRepository;

    public void createGame(final UUID gameId) {
        gameRepository.save(new Game(gameId));
    }

    public void enter(final UUID gameId, final UUID playerId) {
        Game game = getGame(gameId);
        gameRepository.save(game.enter(new Player(playerId)));
    }

    public void readyToPlay(final UUID gameId, final UUID playerId) {
        Game game = getGame(gameId);
        gameRepository.save(game.readyToStart(playerId));
    }

    public Field startGame(final UUID gameId, final UUID playerId) {
        Game game = getGame(gameId);
        Field field = game.startGame(playerId);
        fieldRepository.save(field);
        return field;
    }

    private Game getGame(final UUID gameId) {
        return gameRepository.getById(gameId)
                .orElseThrow(() -> new DomainException("Game not found"));
    }
}
