package lineball.server.domain.game.events;

import lineball.server.domain.DomainEvent;
import lineball.server.domain.game.PlayerType;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class DeadEndEvent implements DomainEvent {

    private static final String type = "game.dead.end";

    UUID aggregateId;
    String eventType;
    LocalDateTime createdAt;
    PlayerType playerType;

    public DeadEndEvent(UUID gameId, PlayerType playerType) {
        this.aggregateId = gameId;
        this.eventType = type;
        this.createdAt = LocalDateTime.now();
        this.playerType = playerType;
    }
}
