package lineball.server.app.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import lineball.server.domain.FieldFacade;
import lineball.server.domain.game.command.ActionCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameHandler implements WebSocketHandler {

    private static final ObjectMapper mapper = new ObjectMapper();

    private final FieldFacade fieldFacade;

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        return webSocketSession.receive()
                .doOnNext(m -> {
                    try {
                        ActionCommand action = mapper.readValue(m.getPayloadAsText(), ActionCommand.class);
                        fieldFacade.makeMove(action, UUID.randomUUID());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .then();
    }
}
