package lineball.server.domain;

import lineball.server.domain.field.Field;
import lineball.server.domain.field.FieldRepository;
import lineball.server.domain.game.GameRepository;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class FieldFacade {

    private final FieldRepository fieldRepository;
    private final GameRepository gameRepository;

    public void newField(UUID fieldId) {
        Field field = new Field(fieldId);
        fieldRepository.save(field);
    }

    public void enter(UUID fieldId, UUID playerId) {
        Field field = fieldRepository.getById(fieldId)
                .orElseThrow(() -> new DomainException("Field not found"));
        field.enter(new Player(playerId));
    }

    public void readyToPlay(UUID playerId) {
        Field field = fieldRepository.getByPlayerId(playerId)
                .orElseThrow(() -> new DomainException("Field not found"));
        field.readyToStart(playerId);
    }

    public void startGame(UUID playerId) {
        Field field = fieldRepository.getByPlayerId(playerId)
                .orElseThrow(() -> new DomainException("Field not found"));
        field.startGame(playerId);
    }
}
