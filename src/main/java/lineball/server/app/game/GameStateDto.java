package lineball.server.app.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lineball")
@Data
@AllArgsConstructor
public class GameStateDto {

    @Id
    private String id;

    private int x;

    private int y;
}
