package lineball.server.app.game;

import lineball.server.domain.FieldFacade;
import lineball.server.persistence.memory.FieldInMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public FieldFacade getGameFacade() {
        return new FieldFacade(new FieldInMemoryRepository());
    }
}
