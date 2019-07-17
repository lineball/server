package lineball.server.domain.game.dot;

import lineball.server.domain.DomainException;
import lineball.server.domain.game.PlayerType;
import lombok.Value;

import java.util.Optional;


@Value
public class Size {
    int xBorder;
    int yBorder;
    int goalBorder = 1;


    int getAvailable(Coordinate coordinate) {
        if (isGoal(coordinate)) {
            return 1;
        }
        int absX = Math.abs(coordinate.getX());
        int absY = Math.abs(coordinate.getY());
        if (absX > xBorder || absY > yBorder) {
            throw new DomainException("Coordinate points outside the border");
        }
        if (absX == xBorder) {
            if (absY == yBorder) {
                return 1;
            }
            return 3;
        }

        if (absY == yBorder) {
            if (absX == 0) {
                return 8;
            }
            if (absX == 1) {
                return 5;
            }
            return 3;
        }

        return Dot.MAX_AVAILABLE;
    }

    private boolean isGoal(Coordinate coordinate) {
        return coordinate.absY() == yBorder + 1 && coordinate.absX() <= goalBorder;
    }

    public Optional<PlayerType> lostGoal(Coordinate coordinate) {
        boolean isGol = isGoal(coordinate);
        if (!isGol) {
            return Optional.empty();
        }

        if (coordinate.getY() > 0) {
            return Optional.of(PlayerType.WHITE);
        } else {
            return Optional.of(PlayerType.BLACK);
        }
    }
}
