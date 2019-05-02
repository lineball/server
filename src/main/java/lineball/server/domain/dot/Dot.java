package lineball.server.domain.dot;

import lineball.server.domain.exception.DeadDotException;
import lineball.server.domain.exception.DomainException;
import lineball.server.domain.exception.GoalDotException;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

import static lineball.server.domain.field.FieldConstants.*;

@Getter
@EqualsAndHashCode(exclude = "dots")
@AllArgsConstructor
public class Dot {
  int x;
  int y;
  Set<Dot> dots;

  public Dot(int x, int y) {
    this.x = x;
    this.y = y;
    this.dots = new HashSet<>();
  }

  public void addToConnected(Dot dot) {
    if (dot.isLoseMove()) {
      throw new DeadDotException("looses game");
    }
    if (dot.isWinMove()) {
      throw new GoalDotException("won game");
    }
    if(isAccessible(dot)) {
      dots.add(dot);
      dot.getDots().add(this);
      return;
    }
    throw new DomainException("Cannot add dot to path");
  }

  private boolean isNotMoveFromSideOrGoalLine() {
    return isSideOrGoalLine() &&
            dots.size() == MAX_SIDE_LINE_MOVES;
  }

  public boolean isSideOrGoalLine() {
    return isGoalLine() || isSideLine();
  }

  public boolean isAccessible(Dot dot) {

    return canMove( dot )
            && !(isSideLine() && dot.getX() >= x)
            && !(isGoalLine() && dot.getY() >= y)
            && canConnectWith(dot);
  }

  private boolean canMove(Dot dot) {
    return Math.abs(dot.getX() - this.x) <= 1 &&
           Math.abs(dot.getY() - this.y) <= 1 &&
           !this.equals(dot);
  }

  private boolean canConnectWith(Dot dot) {
    return !(dots.contains(dot) || dot.getDots().contains(this));
  }

  private boolean isSideLine() {
    return Math.abs(x) == FIELD_WIDTH;
  }

  private boolean isGoalLine() {
    return Math.abs(y) == FIELD_HEIGHT &&
           Math.abs(x) >= 1 && Math.abs(x)< FIELD_WIDTH;
  }

  private boolean isLoseMove() {
    return (Math.abs(x) == FIELD_WIDTH && Math.abs(y) == FIELD_HEIGHT) ||
            isNotMoveFromSideOrGoalLine() ||
            this.getDots().size() == 7;
  }

  private boolean isWinMove() {
   return Math.abs(y) == GOAL_LINE;
  }
}
