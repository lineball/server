package lineball.server.domain.game.command;

import lineball.server.domain.game.PlayerType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class EndGameCommand {
    private UUID gameId;
    private PlayerType winner;
}
