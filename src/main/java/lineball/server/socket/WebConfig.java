package lineball.server.socket;

import lineball.server.app.game.GameHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.reactive.socket.server.upgrade.TomcatRequestUpgradeStrategy;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Configuration
public class WebConfig {

    private static final String GAME_END_POINT = "/game";

    private final GameHandler gameHandler;

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter(webSocketService());
    }

    @Bean
    public WebSocketService webSocketService() {
        TomcatRequestUpgradeStrategy strategy = new TomcatRequestUpgradeStrategy();
        strategy.setMaxSessionIdleTimeout(0L);
        return new HandshakeWebSocketService(strategy);
    }

    @Bean
    public HandlerMapping gameHandlerMapping() {
        //handlers
        var map = Map.of(GAME_END_POINT, gameHandler);

        var corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);

        var corsMap = Map.of(GAME_END_POINT, corsConfiguration);

        return createUrlHandler(map, corsMap);
    }

    private HandlerMapping createUrlHandler(final Map<String, GameHandler> map,
                                            final Map<String, CorsConfiguration> corsMap) {
        var mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(10); // before annotated controllers
        mapping.setUrlMap(map);
        mapping.setCorsConfigurations(corsMap);
        return mapping;
    }
}
