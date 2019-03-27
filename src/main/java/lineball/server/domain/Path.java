package lineball.server.domain;

import lineball.server.domain.dot.Dot;

import java.util.Deque;
import java.util.LinkedList;

public class Path {

  private LinkedList<Dot> dots;

  public Path() {
    dots = new LinkedList<>();
    dots.add(new Dot(0,0));
  }

  public Deque<Dot> getDots() {
    return new LinkedList<>(dots);
  }

  public void addDot(Dot dot) {
    Dot newDot = findDotInPath(dot);
    if (newDot.isEnd()) {
      throw new DomainException("End game");
    }
    if(dots.getLast().isAccessible(newDot)) {
      dots.getLast().addToConnected(newDot);
      dots.add(newDot);
      return;
    }
    throw new DomainException("Cannot add dot to path");
  }

  public void removeDot() {
    if (!dots.isEmpty()) {
      Dot dot = dots.removeLast();
      dots.forEach(d -> d.getDots().remove(dot));
    }
  }

  private Dot findDotInPath(Dot dot) {
    int i = dots.indexOf(dot);
    return i == -1 ? dot : dots.get(i);
  }
}
