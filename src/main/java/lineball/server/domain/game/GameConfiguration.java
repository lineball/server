package lineball.server.domain.game;

import lineball.server.domain.EventStoreRepository;
import lineball.server.persistence.memory.EventStoreInMemoryRepository;
import lineball.server.persistence.memory.GameInMemoryRepository;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfiguration {

    @Getter
    private EventStoreRepository eventStoreRepository;

    public GameConfiguration() {
        this.eventStoreRepository = new EventStoreInMemoryRepository();
    }

    GameFacade gameFacade() {
        return new GameFacade(eventStoreRepository, new GameInMemoryRepository());
    }
}
