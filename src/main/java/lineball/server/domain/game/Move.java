package lineball.server.domain.game;

import lineball.server.domain.DomainException;
import lineball.server.domain.game.dot.Dot;
import lombok.Getter;

import static java.lang.Math.abs;

@Getter
class Move {
    Dot from;
    Dot to;
    PlayerType player;

    Move(Dot from, Dot to, PlayerType player) {
        distance(from, to);
        this.from = from.use();
        this.to = to.use();
        this.player = player;
    }

    private void distance(Dot from, Dot to) {
        int xDistance = abs(from.getCoordinate().getX() - to.getCoordinate().getX());
        int yDistance = abs(from.getCoordinate().getY() - to.getCoordinate().getY());
        if (xDistance > 1 || yDistance > 1) {
            throw new DomainException("Incorrect move");
        }
    }
}
