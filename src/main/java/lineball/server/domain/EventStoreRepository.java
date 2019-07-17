package lineball.server.domain;

import java.util.List;

public interface EventStoreRepository {

    List<DomainEvent> findAll();

    void save(List<DomainEvent> events);
}
