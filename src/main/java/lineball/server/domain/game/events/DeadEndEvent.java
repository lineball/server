package lineball.server.domain.game.events;

import lineball.server.domain.DomainEvent;
import lineball.server.domain.game.PlayerType;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class DeadEndEvent implements DomainEvent {

    public static final String type = "game.dead.end";

    UUID aggregateId;
    String eventType;
    LocalDateTime createdAt;
    PlayerType playerWin;

    public DeadEndEvent(UUID gameId, PlayerType playerType) {
        this.aggregateId = gameId;
        this.eventType = type;
        this.createdAt = LocalDateTime.now();
        this.playerWin = playerType;
    }
}
