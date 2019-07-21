package lineball.server.domain.game;

import lineball.server.domain.DomainException;
import lineball.server.domain.EventStoreRepository;
import lineball.server.domain.dto.FieldDto;
import lineball.server.domain.field.FieldFacade;
import lineball.server.domain.game.command.ActionCommand;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class GameFacade {

    private FieldFacade fieldFacade;
    private EventStoreRepository eventStoreRepository;
    private GameRepository gameRepository;

    public void startGame(UUID playerId) {
        FieldDto field = fieldFacade.fieldByPlayerId(playerId);
        if (fieldFacade.readyToPlay(field.getId())) {
            gameRepository.save(new Game(field.getId()));
        } else {
            throw new DomainException("dd");
        }
    }

    public void makeMove(ActionCommand command, UUID playerId) {
        FieldDto field = fieldFacade.fieldByPlayerId(playerId);
        PlayersOnField playersOnField = new PlayersOnField(field.getId(), field.getWhiteId(), field.getBlackId());
        Game game = gameRepository.getActiveByFieldId(field.getId());
        game.move(command, playersOnField.typeById(playerId));
        eventStoreRepository.save(game.getEvents());
        gameRepository.save(game);
    }
}
