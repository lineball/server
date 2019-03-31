package lineball.server.domain.exception;

import lineball.server.domain.DomainException;

public class DeadDotException extends DomainException {

  public DeadDotException(String message) {
    super(message);
  }
}
