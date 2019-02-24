package lineball.server.domain.field;

import lineball.server.domain.DomainException;
import lineball.server.domain.Player;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

public class Field {

    @Getter
    private UUID id;
    private Player white;
    private Player black;

    public Field(UUID id) {
        this.id = id;
    }

    public void enter(Player player) {
        if (Objects.isNull(white)) {
            white = player;
        } else if (Objects.isNull(black)) {
            black = player;
        } else {
            throw new DomainException("Cannot enter field if there are already two players");
        }
    }

    public void readyToStart(UUID playerId) {

    }

    public void startGame(UUID playerId) {
        if (Objects.isNull(white) || Objects.isNull(black)) {
            throw new DomainException("Cannot init game without second player");
        }
    }

    public boolean hasPlayerOn(UUID playerId) {
        return (Objects.nonNull(white) && white.getId().equals(playerId)) ||
                (Objects.nonNull(black) && black.getId().equals(playerId));
    }
}
