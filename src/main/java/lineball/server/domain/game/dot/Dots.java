package lineball.server.domain.game.dot;

import lombok.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Value
public class Dots {

    Size size;
    Map<Coordinate, Dot> dotList;

    public Dots(Size size) {
        this.size = size;
        this.dotList = new HashMap<>();
    }

    public Dot getByCoordinates(Coordinate coordinate) {
        return Optional.ofNullable(dotList.get(coordinate))
                .orElseGet(() -> createDot(coordinate));
    }

    private Dot createDot(Coordinate coordinate) {
        size.getAvailable(coordinate);
        Dot dot = new FreshDot(coordinate, size.getAvailable(coordinate));
        dotList.put(coordinate, dot);
        return dot;
    }
}
