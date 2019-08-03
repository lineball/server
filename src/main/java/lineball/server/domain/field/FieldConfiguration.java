package lineball.server.domain.field;

import lineball.server.persistence.memory.FieldInMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FieldConfiguration {

    @Bean
    FieldFacade fieldFacade() {
        return new FieldFacade(new FieldInMemoryRepository());
    }
}
