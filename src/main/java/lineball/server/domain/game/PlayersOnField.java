package lineball.server.domain.game;

import lineball.server.domain.DomainException;
import lombok.Value;

import java.util.UUID;

@Value
class PlayersOnField {
    UUID fieldId;
    UUID white;
    UUID black;

    PlayerType typeById(UUID playerId) {
        PlayerType type;
        if (playerId.equals(black)) {
            type = PlayerType.BLACK;
        } else if (playerId.equals(white)) {
            type = PlayerType.WHITE;
        } else {
            throw new DomainException("Player no found");
        }
        return type;
    }
}
