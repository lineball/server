package lineball.server.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public interface DomainEvent {
    UUID getAggregateId();

    String getEventType();

    LocalDateTime getCreatedAt();
}
