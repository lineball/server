package lineball.server.domain.game;

import lineball.server.domain.DomainException;
import lineball.server.domain.game.dot.Dot;
import lombok.Getter;
import lombok.Value;

import java.util.LinkedList;

@Value
public class Path {
    @Getter
    LinkedList<Move> moves;
    Dot start;

    public Path(Dot start) {
        moves = new LinkedList<>();
        this.start = start;
    }

    public void addMove(Dot to, PlayerType playerType) {
        Dot from = getFrom();

        if (!moves.isEmpty() && from.getAvailable() == 7 && moves.getLast().player.equals(playerType)) {
            throw new DomainException("a");
        }
        moves.add(new Move(from, to, playerType));
    }

    private Dot getFrom() {
        if (moves.isEmpty()) {
            return start;
        }
        return moves.getLast().getTo();
    }
}
