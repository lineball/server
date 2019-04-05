package lineball.server.app.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import lineball.server.domain.GameFacade;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.io.IOException;

@AllArgsConstructor
@Service
public class GameHandler implements WebSocketHandler {

    private static final ObjectMapper mapper = new ObjectMapper();

    private final GameFacade gameFacade;

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        return webSocketSession.receive()
                .doOnNext(m -> {
                    try {
                        ActionCommand action = mapper.readValue(m.getPayloadAsText(), ActionCommand.class);
                        gameFacade.makeMove(action.getX(), action.getY(), action.getType().name(), "tadzio");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .then();
    }
}
