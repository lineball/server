package lineball.server.app.game.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "lineball")
@Data
@AllArgsConstructor
public class GameDto {

    @Id
    private String id;

    private Map<PlayerType, String> players;

    private List<TurnDto> state;
}
