package lineball.server.domain;

import lineball.server.domain.dot.Dot;
import lombok.Getter;

import java.util.UUID;

public class Player {

    @Getter
    private UUID id;

    public Player(UUID id) {
        this.id = id;
    }

    public void move(Dot dot, Path path) {
        path.addDot(dot);
    }

    public void revert(Path path) {
        path.removeDot();
    }
}
