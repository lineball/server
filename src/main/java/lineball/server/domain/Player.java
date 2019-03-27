package lineball.server.domain;

import lineball.server.domain.dot.Dot;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Player {

    @Getter
    private UUID id;

    private Set<Dot> myDots;

    public Player(UUID id) {
        this.id = id;
        this.myDots = new HashSet<>();
    }

    public void move(Dot dot, Path path) {
        path.addDot(dot);
        myDots.add(dot);
    }

    public void revert(Path path) {
            path.removeDot();
    }

    public boolean isCanRevert(Dot dot) {
       return myDots.contains(dot);
    }

    public boolean isCanMove(Dot dot, Path path) {
       return !path.getDots().contains(dot) &&
              !dot.isSideOrGoalLine();
    }
}
