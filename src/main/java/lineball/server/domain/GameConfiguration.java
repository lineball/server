package lineball.server.domain;

import lineball.server.domain.game.GameFacade;
import lineball.server.persistence.memory.GameInMemoryRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfiguration {

    GameFacade gameFacade() {
        return new GameFacade(new GameInMemoryRepository());
    }
}
