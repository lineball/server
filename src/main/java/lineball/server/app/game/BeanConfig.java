package lineball.server.app.game;

import lineball.server.domain.GameFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public GameFacade getGameFacade() {
        return new GameFacade();
    }
}
