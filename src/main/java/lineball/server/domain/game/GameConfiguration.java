package lineball.server.domain.game;

import lineball.server.domain.EventStoreRepository;
import lineball.server.domain.field.FieldFacade;
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

    GameFacade gameFacade(FieldFacade fieldFacade) {
        return new GameFacade(fieldFacade, eventStoreRepository, new GameInMemoryRepository());
    }
}
