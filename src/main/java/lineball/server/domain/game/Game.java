package lineball.server.domain.game;

import lineball.server.domain.Player;
import lineball.server.domain.game.dot.*;
import lineball.server.domain.field.Field;
import lineball.server.domain.game.command.ActionCommand;
import lombok.Getter;

import java.util.LinkedList;
import java.util.UUID;

public class Game {

    @Getter
    private final UUID id;
    private final UUID fieldId;
    @Getter
    private final Player white;
    @Getter
    private final Player black;
    private final Path path;
    private final Dots dots;

    public Game(Field field, Dot start) {
        this.id = UUID.randomUUID();
        this.fieldId = field.getId();
        this.white = field.getWhite();
        this.black = field.getBlack();
        this.dots = new Dots(new Coordinate(8, 8));
        this.path = new Path(dots.getByCoordinates(new Coordinate(0, 0)));
    }

    public void move(ActionCommand action, UUID playerId) {
        Coordinate coordinate = new Coordinate(action.getX(), action.getY());
        PlayerType type;
        if (playerId.equals(black.getId())) {
            type = PlayerType.BLACK;
        } else if (playerId.equals(white.getId())) {
            type = PlayerType.WHITE;
        } else {
            throw new RuntimeException();
        }


        path.addMove(dots.getByCoordinates(coordinate), type);
    }


    public LinkedList<Move> getMoves() {
        return path.getMoves();
    }
}
