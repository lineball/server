package lineball.server.domain.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionConstant {

  public static final Supplier<DomainException> FIELD_NOT_FOUND = () -> new DomainException("Field not found");
}
