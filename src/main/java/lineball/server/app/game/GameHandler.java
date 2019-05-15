package lineball.server.app.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import lineball.server.app.game.dto.GameDto;
import lineball.server.domain.GameFacade;
import lineball.server.domain.game.command.ActionCommand;
import lineball.server.persistence.mongodb.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.UUID;

@AllArgsConstructor
@Service
public class GameHandler implements WebSocketHandler {

    private static final ObjectMapper mapper = new ObjectMapper();

    private final GameFacade gameFacade;

    private final GameRepository gameRepository;

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {

        webSocketSession.receive()
                .doOnNext(m -> {
                    try {
                        ActionCommand action = mapper.readValue(m.getPayloadAsText(), ActionCommand.class);
                        gameFacade.makeMove(action, UUID.randomUUID());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .then();


        GameDto dto = new GameDto("x", 5, 6);

        Mono<Void> then = gameRepository.save(dto).then();

        gameRepository.findAll().log().map(GameDto::getId)
                .subscribe(System.out::println);

        Flux<GameDto> source = gameRepository.findAll();
        return webSocketSession.send(source
                .map(msg -> "RECEIVED ON SERVER xd :: " + msg.getX())
                .map(webSocketSession::textMessage)
        );
    }
}
