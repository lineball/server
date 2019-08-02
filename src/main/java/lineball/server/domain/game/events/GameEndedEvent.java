package lineball.server.domain.game.events;

import lineball.server.domain.eventstore.DomainEvent;
import lineball.server.domain.game.PlayerType;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class GameEndedEvent implements DomainEvent {

    public static final String type = "game.ended";

    UUID aggregateId;
    String eventType;
    LocalDateTime createdAt;
    PlayerType playerWin;

    public GameEndedEvent(UUID gameId, PlayerType playerType) {
        this.aggregateId = gameId;
        this.eventType = type;
        this.createdAt = LocalDateTime.now();
        this.playerWin = playerType;
    }
}
