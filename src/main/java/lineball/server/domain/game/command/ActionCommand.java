package lineball.server.domain.game.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ActionCommand {

    private ActionType type;
    private int x;
    private int y;
}
