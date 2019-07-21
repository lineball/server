package lineball.server.domain.field;

import lineball.server.persistence.memory.FieldInMemoryRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FieldConfiguration {

    FieldFacade fieldFacade() {
        return new FieldFacade(new FieldInMemoryRepository());
    }
}
