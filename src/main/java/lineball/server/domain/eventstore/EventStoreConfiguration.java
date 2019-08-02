package lineball.server.domain.eventstore;

import lineball.server.persistence.memory.EventStoreInMemoryRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventStoreConfiguration {

    EventPublisher eventPublisher() {
        return new EventPublisher(new EventStoreInMemoryRepository());
    }
}
