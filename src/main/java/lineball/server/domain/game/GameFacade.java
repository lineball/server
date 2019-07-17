package lineball.server.domain.game;

import lineball.server.domain.EventStoreRepository;
import lineball.server.domain.game.command.ActionCommand;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
public class GameFacade {

    private EventStoreRepository eventStoreRepository;
    private GameRepository gameRepository;

    public void startGame() {
    }

    public void makeMove(ActionCommand command, UUID playerId) {
        Game game = gameRepository.getByPlayerId(playerId);
        game.move(command, playerId);
        eventStoreRepository.save(game.getEvents());
        gameRepository.save(game);
    }

    public Collection<Move> getMoves(UUID playerId) {
        Game game = gameRepository.getByPlayerId(playerId);
        return game.getMoves();
    }
}
