package lineball.server.domain.field;

import lineball.server.domain.DomainException;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static lineball.server.domain.field.FieldStatus.NEW;
import static lineball.server.domain.field.FieldStatus.READY_TO_PLAY;

@Getter
public class Field {

    private FieldStatus fieldStatus;
    private final UUID id;
    private Player white;
    private Player black;

    public Field(UUID id) {
        this.id = id;
        this.fieldStatus = NEW;
    }

    public void enter(Player player) {
        if (isNull(white)) {
            white = player;
        } else if (isNull(black)) {
            black = player;
        } else {
            throw new DomainException("Cannot enter field if there are already two players");
        }

        if (playersEnters()) {
            fieldStatus = READY_TO_PLAY;
        }
    }

    public boolean readyToStart() {
        return fieldStatus.equals(READY_TO_PLAY);
    }

    public boolean hasPlayerOn(UUID playerId) {
        return (nonNull(white) && white.getId().equals(playerId)) ||
                (nonNull(black) && black.getId().equals(playerId));
    }

    private boolean playersEnters() {
        return nonNull(white) && nonNull(black);
    }
}
