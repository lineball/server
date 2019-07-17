package lineball.server.domain.game.dot;

import lombok.Value;

@Value
public class Coordinate {
    int x;
    int y;

    public int absX() {
        return Math.abs(x);
    }

    public int absY() {
        return Math.abs(y);
    }
}
