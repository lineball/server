package lineball.server.domain.game;

public enum PlayerType {
    WHITE,
    BLACK;

    public PlayerType getOpposite() {
        return this.equals(WHITE) ? BLACK : WHITE;
    }
}
