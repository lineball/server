package lineball.server.domain.game.events;

import lineball.server.domain.DomainEvent;
import lineball.server.domain.game.PlayerType;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class GoalScoredEvent implements DomainEvent {

    public static final String type = "game.goal.scored";

    UUID aggregateId;
    String eventType;
    LocalDateTime createdAt;
    PlayerType playerType;

    public GoalScoredEvent(UUID aggregateId, PlayerType playerType) {
        this.aggregateId = aggregateId;
        this.eventType = type;
        this.createdAt = LocalDateTime.now();
        this.playerType = playerType;
    }
}
