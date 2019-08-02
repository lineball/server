package lineball.server.persistence.memory;

import lineball.server.domain.eventstore.DomainEvent;
import lineball.server.domain.eventstore.EventStoreRepository;
import lineball.server.domain.eventstore.EventStream;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

public class EventStoreInMemoryRepository implements EventStoreRepository {

    private final ConcurrentHashMap<UUID, EventStream> map = new ConcurrentHashMap<>();

    @Override
    public Optional<EventStream> findByAggregateUUID(UUID uuid) {
        return ofNullable(map.get(uuid));
    }

    @Override
    public void save(UUID aggregateId, List<DomainEvent> events) {
        EventStream stream = findByAggregateUUID(aggregateId)
                .orElseGet(() -> new EventStream(aggregateId));
        stream.addEvents(events);
        map.put(aggregateId, stream);
    }

    @Override
    public List<DomainEvent> getEventsForAggregate(UUID aggregateId) {
        return findByAggregateUUID(aggregateId)
                .map(EventStream::getEvents)
                .orElse(emptyList());
    }
}
