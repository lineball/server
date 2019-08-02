package lineball.server.domain.game.dot;

import lineball.server.domain.DomainException;
import lombok.Getter;

@Getter
public class Dot {

    public static final int MAX_AVAILABLE = 8;
    public static final int BORDER_AVAILABLE = 3;

    Coordinate coordinate;
    int available;

    Dot(Coordinate coordinate, int available) {
        if (available > MAX_AVAILABLE) {
            throw new DomainException("Max available move directions for new dot is " + MAX_AVAILABLE);
        }
        if (available < 1) {
            throw new DomainException("Can't create dot with less than 1 available move directions");
        }
        this.coordinate = coordinate;
        this.available = available;
    }

    public Dot use() {
        if (available < 1) {
            throw new DomainException("Cannot use dot where is no available move directions");
        }
        available = available - 1;
        return this;
    }
}
