package lineball.server.persistence.mongodb;

import lineball.server.app.game.dto.GameDto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface GameRepository extends ReactiveCrudRepository<GameDto, String> {

    Flux<GameDto> findAllById(String card);
}
