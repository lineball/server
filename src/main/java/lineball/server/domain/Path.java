package lineball.server.domain;

import lineball.server.domain.dot.Dot;

import java.util.LinkedList;

public class Path {

  LinkedList<Dot> dots = new LinkedList<>();

  public void addDot(Dot dot) {
    if (dot.isEnd()) {
      throw new DomainException("End game");
    }
    if(!dots.isEmpty() && dots.getLast().isAccessible(dot)) {
      dots.add(dot);
    }
    throw new DomainException("Cannot add dot to path");
  }

  public void removeDot() {
    if (!dots.isEmpty()) {
      dots.removeLast();
    }
  }
}
