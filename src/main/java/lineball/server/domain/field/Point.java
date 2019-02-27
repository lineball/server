package lineball.server.domain.field;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Point {

    private int x;
    private int y;

    public boolean isLegal() {
        return (Math.abs(y) <= 5 && Math.abs(x) <= 4)
                || isWinning();
    }

    public boolean isWinning() {
        return Math.abs(y) == 6 && Math.abs(x) <= 1;
    }

    public boolean isOnSideline() {
        return Math.abs(x) == 4;
    }

    public boolean isOnEndline() {
        return Math.abs(y) == 5
                && x != 0;
    }

}
