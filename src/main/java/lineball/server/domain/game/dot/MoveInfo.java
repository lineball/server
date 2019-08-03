package lineball.server.domain.game.dot;

import lineball.server.domain.DomainException;
import lineball.server.domain.game.PlayerType;
import lombok.Value;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;


@Value
public class MoveInfo {
    int xBorder;
    int yBorder;
    int goalBorder = 1;


    int getAvailable(Coordinate coordinate) {
        if (isGoal(coordinate)) {
            return 1;
        }
        if (coordinate.absX() > xBorder || coordinate.absY() > yBorder) {
            throw new DomainException("Coordinate points outside the border");
        }
        if (coordinate.absX() == xBorder) {
            if (coordinate.absY() == yBorder) {
                return 1;
            }
            return 3;
        }

        if (coordinate.absY() == yBorder) {
            if (coordinate.absX() == 0) {
                return 8;
            }
            if (coordinate.absX() == 1) {
                return 5;
            }
            return 3;
        }

        return Dot.MAX_AVAILABLE;
    }

    Optional<PlayerType> lostGoal(Coordinate coordinate) {
        if (!isGoal(coordinate)) {
            return empty();
        }

        if (coordinate.getY() > 0) {
            return of(PlayerType.WHITE);
        } else {
            return of(PlayerType.BLACK);
        }
    }

    private boolean isGoal(Coordinate coordinate) {
        return coordinate.absY() == yBorder + 1 && coordinate.absX() <= goalBorder;
    }
}
