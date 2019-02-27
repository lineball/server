package lineball.server.domain.field;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Move {

    private Point startPoint;
    private Point endPoint;

    public boolean isWinning() {
        return endPoint.isWinning();
    }

    public boolean isLegal() {
        return startPoint != endPoint
                && endPoint.isLegal()
                && pointsAreClose()
                && !(startPoint.isOnEndline() && endPoint.isOnEndline())
                && !(startPoint.isOnSideline() && endPoint.isOnSideline());
    }

    private boolean pointsAreClose() {
        return Math.abs(startPoint.getX() - endPoint.getX()) <= 1
                && Math.abs(startPoint.getY() - endPoint.getY()) <= 1;
    }

}
