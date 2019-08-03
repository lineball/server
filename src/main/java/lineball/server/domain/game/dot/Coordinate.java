package lineball.server.domain.game.dot;

import lombok.Value;

import static java.lang.Math.*;

@Value
public class Coordinate {
    int x;
    int y;

    int absX() {
        return abs(x);
    }

    int absY() {
        return abs(y);
    }
}
