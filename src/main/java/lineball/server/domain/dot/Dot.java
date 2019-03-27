package lineball.server.domain.dot;

import lineball.server.domain.DomainException;
import lombok.*;

import java.util.*;

import static lineball.server.domain.FieldConstants.*;

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
    if (dot.isEnd()) {
      throw new DomainException("End game");
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
    if (isSideLine()) {
      return Math.abs(dot.getX()) < Math.abs(x) && canMove(dot);
    } else if (isGoalLine()) {
      return Math.abs(dot.getY()) < Math.abs(y) && canMove(dot);
    }
    return canMove(dot) && isConnected(dot);
  }

  private boolean canMove(Dot dot) {
    int dx = Math.abs(dot.getX() - this.x);
    int dy = Math.abs(dot.getY() - this.y);
    return dx <= 1  && dy <= 1 &&
            (dx > 0 || dy > 0);
  }

  private boolean isConnected(Dot dot) {
    return !(dots.contains(dot) || dot.getDots().contains(this));
  }

  private boolean isSideLine() {
    return Math.abs(x) == FIELD_WIDTH;
  }

  private boolean isGoalLine() {
    return Math.abs(y) == FIELD_HEIGHT &&
            Math.abs(x) >= 1 && Math.abs(x)< FIELD_WIDTH;
  }

  private boolean isEnd() {
    return ((Math.abs(x) == FIELD_WIDTH && Math.abs(y) == FIELD_HEIGHT) ||
            Math.abs(y) == GOAL_LINE) || isNotMoveFromSideOrGoalLine() ||
            this.getDots().size() == 7;
  }
}
