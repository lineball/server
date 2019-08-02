package lineball.server.domain.eventstore;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class EventPublisher {

    EventStoreRepository repository;

    public void publish(Aggregate aggregate, List<DomainEvent> domainEvents) {
        repository.save(aggregate.getId(), domainEvents);
        aggregate.clearPublishedEvents();
    }
}
