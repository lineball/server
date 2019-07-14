package lineball.server.domain.game.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActionCommand {

    private ActionType type;
    private int x;
    private int y;
}
