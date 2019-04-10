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
        Map<String, WebSocketHandler> map = new HashMap<>();
        map.put(GAME_END_POINT, gameHandler);

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);

        Map<String, CorsConfiguration> corsConfigurationMap = new HashMap<>();
        corsConfigurationMap.put(GAME_END_POINT, corsConfiguration);


        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(10); // before annotated controllers
        mapping.setUrlMap(map);
        mapping.setCorsConfigurations(corsConfigurationMap);
        return mapping;
    }
}
