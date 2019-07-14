package lineball.server.domain.game;

import lineball.server.domain.DomainException;
import lineball.server.domain.game.PlayerType;
import lineball.server.domain.game.dot.Dot;
import lombok.Getter;

@Getter
public class Move {
    Dot from;
    Dot to;
    PlayerType player;

    public Move(Dot from, Dot to, PlayerType player) {
        distance(from, to);
        this.from = from.use();
        this.to = to.use();
        this.player = player;
    }

    private void distance(Dot from, Dot to) {
        int xDistance = Math.abs(from.getCoordinate().getX() - to.getCoordinate().getX());
        int yDistance = Math.abs(from.getCoordinate().getY() - to.getCoordinate().getY());
        if (xDistance > 1 || yDistance > 1) {
            throw new DomainException("e");
        }
    }
}
