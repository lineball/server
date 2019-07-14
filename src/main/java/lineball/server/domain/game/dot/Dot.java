package lineball.server.domain.game.dot;

import lombok.Getter;

@Getter
public class Dot {

    public static final int MAX_AVAILABLE = 8;
    public static final int BORDER_AVAILABLE = 3;

    Coordinate coordinate;
    int available;

    public Dot(Coordinate coordinate, int available) {
        if (available > MAX_AVAILABLE) {
            throw new RuntimeException();
        }
        if (available < 1) {
            throw new RuntimeException();
        }
        this.coordinate = coordinate;
        this.available = available;
    }

    public Dot use() {
        if (available < 1) {
            throw new RuntimeException();
        }
        available = available - 1;
        return this;
    }
}
