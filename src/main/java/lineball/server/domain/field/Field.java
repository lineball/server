package lineball.server.domain.field;

import lineball.server.domain.DomainException;
import lineball.server.domain.Path;
import lineball.server.domain.Player;
import lineball.server.domain.PlayerColor;
import lineball.server.domain.dot.Dot;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class Field {

    private UUID id;
    private Player white;
    private Player black;
    private Player currentPlayer;
    private Path path;

    public Field(UUID id) {
        this.id = id;
        this.path = new Path();
    }

    public void enter(Player player) {
        if (Objects.isNull(white)) {
            white = player;
            player.setColor(PlayerColor.WHITE);
            currentPlayer = player;
        } else if (Objects.isNull(black)) {
            black = player;
            black.setColor(PlayerColor.BLACK);
        } else {
            throw new DomainException("Cannot enter field if there are already two players");
        }
    }

    public void readyToStart(UUID playerId) {

    }

    public void startGame(UUID playerId) {
        if (Objects.isNull(white) || Objects.isNull(black)) {
            throw new DomainException("Cannot init game without second player");
        }
    }

    public void addMovePlayer(Player player, Dot dot) {
        if (currentPlayer.equals(player)) {
            boolean canMove = player.isCanMove(dot, path);
            player.move(dot, path);
            if (canMove) {
              currentPlayer = player.equals(black) ? white : black;
            }
            return;
        }
        throw new DomainException("Player " + player.getId() + " cannot move");
    }

    public void revertMovePlayer(Player player) {
        Dot last = path.getDots().getLast();
        if (currentPlayer.equals(player) && player.isCanRevert(last)) {
            player.revert(path);
            return;
        }
        throw new DomainException("Player " + player.getId() + " cannot revert");
    }

    public boolean hasPlayerOn(UUID playerId) {
        return (Objects.nonNull(white) && white.getId().equals(playerId)) ||
                (Objects.nonNull(black) && black.getId().equals(playerId));
    }
}
