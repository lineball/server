package lineball.server.domain.game.command;

import lombok.Value;

@Value
public class ActionCommand {
    ActionType type;
    int x;
    int y;
}
