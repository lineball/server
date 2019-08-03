package lineball.server.domain.eventstore;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Value
public class EventStream {

    UUID aggregateUUID;

    List<DomainEvent> events = new ArrayList<>();

    public EventStream(UUID aggregateUUID) {
        this.aggregateUUID = aggregateUUID;
    }

    public void addEvents(List<DomainEvent> events) {
        this.events.addAll(events);
    }

    public List<DomainEvent> getEvents() {
        return events
                .stream()
                .sorted(comparing(DomainEvent::getCreatedAt))
                .collect(toList());
    }

}
