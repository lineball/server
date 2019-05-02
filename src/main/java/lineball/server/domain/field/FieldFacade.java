package lineball.server.domain.field;

import lineball.server.api.DotDto;
import lineball.server.domain.DomainException;
import lineball.server.domain.Player;
import lineball.server.domain.dot.Dot;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class FieldFacade {

    private final FieldRepository fieldRepository;

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

    public void addMovePlayer(UUID fieldId, Player player, DotDto dot) {
        Field field = getFieldById(fieldId);
        field.addMovePlayer(player, new Dot(dot.getX(), dot.getY()));
    }

    public void revertMovePlayer(UUID fieldId, Player player) {
        Field field = getFieldById(fieldId);
        field.revertMovePlayer(player);
    }

    public boolean hasPlayerOn(UUID fieldId, UUID playerId) {
        Field field = getFieldById(fieldId);
        return field.hasPlayerOn(playerId);
    }

    private Field getFieldById(UUID fieldId) {
        return fieldRepository.getById(fieldId)
                      .orElseThrow(() -> new DomainException("Field not found"));
    }
}
