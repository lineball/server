package lineball.server.domain.move;

import lineball.server.domain.dot.Dot;
import lombok.Value;

import java.util.Iterator;
import java.util.List;

@Value
public class Move {

  List<Dot> dots;

  public boolean isAvailable() {
    Iterator<Dot> dotsIterator = dots.iterator();
    while (dotsIterator.hasNext()) {
      boolean accessible = dotsIterator
              .next()
              .isAccessible(dotsIterator.next());
      if(!accessible) {
        return false;
      }
    }
    return true;
  }
}
