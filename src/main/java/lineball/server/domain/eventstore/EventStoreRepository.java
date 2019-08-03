package lineball.server.domain.eventstore;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventStoreRepository {

    Optional<EventStream> findByAggregateUUID(UUID uuid);

    void save(UUID aggregateId, List<DomainEvent> events);

    List<DomainEvent> getEventsForAggregate(UUID aggregateId);
}
