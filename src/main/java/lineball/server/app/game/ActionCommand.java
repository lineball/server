package lineball.server.app.game;

import lombok.Data;

@Data
class ActionCommand {

    ActionType type;
    int x;
    int y;
}
