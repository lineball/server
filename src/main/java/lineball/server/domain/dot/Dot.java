package lineball.server.domain.dot;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@Getter
@EqualsAndHashCode(exclude = "dots")
public class Dot {
  int x;
  int y;
  List<Dot> dots;

  public Dot(int x, int y) {
    this.x = x;
    this.y = y;
    this.dots = new ArrayList<>();
  }

  public boolean isEnd() {
    return (Math.abs(x) == 4 && Math.abs(y) == 5) ||
            Math.abs(y) == 6
            ;
  }

  public void addToConnected(Dot dot) {
    dots.add(dot);
  }

  public boolean isAccessible(Dot dot) {

    if (isBorderSide()) {
      return Math.abs(dot.getX()) < Math.abs(x) && canMove(dot);
    } else if (isBorderGoal()) {
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
    int indexOfThis = dots.indexOf(dot);
    int indexOfDot = dot.getDots().indexOf(this);
    return indexOfThis == -1 && indexOfDot == -1;
  }

  private boolean isBorderSide() {
    return Math.abs(x) == 4;
  }

  private boolean isBorderGoal() {
    return Math.abs(y) == 5 && Math.abs(x) > 1 && Math.abs(x)< 4;
  }
}
