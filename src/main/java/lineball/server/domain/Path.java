package lineball.server.domain;

import lineball.server.domain.dot.Dot;

import java.util.LinkedList;
import java.util.List;

public class Path {

  private LinkedList<Dot> dots;

  public Path() {
    dots = new LinkedList<>();
    dots.add(new Dot(0,0));
  }

  public List<Dot> getDots() {
    return new LinkedList<>(dots);
  }

  public void addDot(Dot dot) {
    if (dot.isEnd()) {
      throw new DomainException("End game");
    }
    int i = dots.indexOf(dot);
    dot = i == -1 ? dot : dots.get(i);
    if(dots.getLast().isAccessible(dot)) {
      dots.getLast().addToConnected(dot);
      dots.add(dot);
      return;
    }
    throw new DomainException("Cannot add dot to path");
  }

  public void removeDot() {
    if (!dots.isEmpty()) {
      dots.removeLast();
    }
  }
}
