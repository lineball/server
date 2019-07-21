package lineball.server.domain.field;

import lineball.server.domain.dto.FieldDto;
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
        Field field = fieldRepository.getById(fieldId);
        field.enter(new Player(playerId));
    }

    public boolean readyToPlay(UUID fieldId) {
        Field field = fieldRepository.getById(fieldId);
        return field.readyToStart();
    }

    public FieldDto fieldByPlayerId(UUID player) {
        Field field = fieldRepository.getByPlayerId(player);
        return new FieldDto(field.getId(), field.getWhite().getId(), field.getBlack().getId());
    }
}
