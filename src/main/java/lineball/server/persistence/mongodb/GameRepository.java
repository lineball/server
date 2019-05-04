package lineball.server.persistence.mongodb;

import lineball.server.app.game.GameStateDto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface GameRepository extends ReactiveCrudRepository<GameStateDto, String> {

    Flux<GameStateDto> findAllById(String card);
}
