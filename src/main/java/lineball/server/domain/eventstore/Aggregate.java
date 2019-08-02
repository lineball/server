package lineball.server.domain.eventstore;

import java.util.UUID;

public interface Aggregate {

    UUID getId();

    void clearPublishedEvents();

}
