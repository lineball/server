package lineball.server.domain.game.dot;

import lineball.server.domain.game.PlayerType;
import lombok.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Value
public class Dots {

    MoveInfo moveInfo;
    Map<Coordinate, Dot> dotList;

    public Dots(MoveInfo moveInfo) {
        this.moveInfo = moveInfo;
        this.dotList = new HashMap<>();
    }

    public Dot getByCoordinates(Coordinate coordinate) {
        return ofNullable(dotList.get(coordinate))
                .orElseGet(() -> createDot(coordinate));
    }

    public Optional<PlayerType> lostGoal(Coordinate coordinate) {
        return moveInfo.lostGoal(coordinate);
    }

    private Dot createDot(Coordinate coordinate) {
        Dot dot = new Dot(coordinate, moveInfo.getAvailable(coordinate));
        dotList.put(coordinate, dot);
        return dot;
    }
}
