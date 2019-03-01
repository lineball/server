package lineball.server.domain.dot;

import lombok.Getter;
import lombok.Value;

@Value
@Getter
public class Dot {
  int x;
  int y;



  public boolean isEnd() {
    return (Math.abs(x) == 4 && Math.abs(y) == 5) ||
            Math.abs(y) == 6;
  }

  public boolean isAccessible(Dot dot) {
    if (isBorderSide()) {
      return Math.abs(dot.getX()) < Math.abs(x) && canMove(dot);
    } else if (isBorderGoal()) {
      return Math.abs(dot.getY()) < Math.abs(y) && canMove(dot);
    }
    return canMove(dot);
  }

  private boolean canMove(Dot dot) {
    return (Math.abs(dot.getX() - this.x) <= 1) &&
    (Math.abs(dot.getY() - this.y) <= 1);
  }

  private boolean isBorderSide() {
    return Math.abs(x) == 4;
  }

  private boolean isBorderGoal() {
    return Math.abs(y) == 5 && Math.abs(x) > 1 && Math.abs(x)< 4;
  }
}
