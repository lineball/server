package lineball.server.domain.field;

import lineball.server.domain.DomainException;
import lineball.server.domain.Path;
import lineball.server.domain.Player;
import lineball.server.domain.dot.Dot;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Field {

    @Getter
    private UUID id;
    private Player white;
    private Player black;
    @Getter
    private Map<UUID, Boolean> whoCanMove;
    @Getter
    private Path path;

    public Field(UUID id) {
        this.id = id;
        this.path = new Path();
        this.whoCanMove = new HashMap<>();
    }

    public void enter(Player player) {
        if (Objects.isNull(white)) {
            white = player;
            whoCanMove.put(white.getId(), true);

        } else if (Objects.isNull(black)) {
            black = player;
            whoCanMove.put(black.getId(), false);
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
        if (whoCanMove.get(player.getId())) {
            boolean canMove = player.isCanMove(dot, path);
            player.move(dot, path);
            if (canMove) {
                whoCanMove.put(player.getId(), false);
                Player otherPlayer = player.equals(black) ? white : black;
                whoCanMove.put(otherPlayer.getId(), true);
            }
            return;
        }
        throw new DomainException("Player " + player.getId() + " cannot move");
    }

    public void revertMovePlayer(Player player) {
        Dot last = path.getDots().getLast();
        if (whoCanMove.get(player.getId()) && player.isCanRevert(last)) {
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
