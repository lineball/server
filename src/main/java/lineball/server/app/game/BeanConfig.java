package lineball.server.app.game;

import lineball.server.domain.GameFacade;
import lineball.server.domain.field.FieldFacade;
import lineball.server.domain.field.FieldRepository;
import lineball.server.persistence.memory.FieldInMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public GameFacade getGameFacade() {
        FieldRepository fieldRepository = new FieldInMemoryRepository();
        FieldFacade fieldFacade = new FieldFacade(fieldRepository);
        return new GameFacade(fieldFacade);
    }
}
