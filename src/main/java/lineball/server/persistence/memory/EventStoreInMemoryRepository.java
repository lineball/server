package lineball.server.persistence.memory;

import lineball.server.domain.DomainEvent;
import lineball.server.domain.EventStoreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class EventStoreInMemoryRepository implements EventStoreRepository {

    private final ConcurrentHashMap<UUID, DomainEvent> map = new ConcurrentHashMap<>();


    @Override
    public List<DomainEvent> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void save(List<DomainEvent> events) {
        events.forEach(e -> map.put(UUID.randomUUID(), e));
    }
}
