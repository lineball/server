package lineball.server.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FieldConstants {
  public static final Integer FIELD_WIDTH = 4;
  public static final Integer FIELD_HEIGHT = 5;
  public static final Integer GOAL_LINE = 6;
  public static final Integer MAX_SIDE_LINE_MOVES = 2;
}
