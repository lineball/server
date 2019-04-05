package lineball.server.domain.game.command;

import lombok.Data;

@Data
public class ActionCommand {

    private ActionType type;
    private int x;
    private int y;
}
