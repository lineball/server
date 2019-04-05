package lineball.server.app.game;

import lombok.Data;

@Data
class ActionCommand {

    private ActionType type;
    private int x;
    private int y;
}
