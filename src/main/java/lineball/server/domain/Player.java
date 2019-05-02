package lineball.server.domain;

import lineball.server.domain.dot.Dot;
import lineball.server.domain.exception.DeadDotException;
import lineball.server.domain.exception.DomainException;
import lineball.server.domain.exception.GoalDotException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(of = "id")
public class Player {

    @Getter
    private UUID id;
    @Setter
    private PlayerColor color;

    private Set<Dot> myDots;

    public Player(UUID id) {
        this.id = id;
        this.myDots = new HashSet<>();
    }

    public void move(Dot dot, Path path) {
        try {
          path.addDot(dot);
        } catch (DeadDotException exception) {
          throw new DomainException("Player: "+ this.id +" "+ exception.getMessage());
        } catch (GoalDotException exception) {
          if((PlayerColor.WHITE.equals(color) && dot.getY() < 0) ||
             (PlayerColor.BLACK.equals(color) && dot.getY() > 0)) {
            throw new GoalDotException("Player: "+ this.id +" looses game");
          }
          throw new DomainException("Player: "+ this.id +" "+ exception.getMessage());
        }
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
