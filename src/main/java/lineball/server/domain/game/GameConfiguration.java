package lineball.server.domain.game;

import lineball.server.domain.eventstore.EventPublisher;
import lineball.server.domain.field.FieldFacade;
import lineball.server.persistence.memory.GameInMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfiguration {

    @Bean
    GameFacade gameFacade(FieldFacade fieldFacade, EventPublisher publisher) {
        return new GameFacade(fieldFacade, publisher, new GameInMemoryRepository());
    }
}
