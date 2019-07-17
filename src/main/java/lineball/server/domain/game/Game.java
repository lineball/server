package lineball.server.domain.game;

import lineball.server.domain.DomainEvent;
import lineball.server.domain.Player;
import lineball.server.domain.game.dot.*;
import lineball.server.domain.field.Field;
import lineball.server.domain.game.command.ActionCommand;
import lineball.server.domain.game.events.GoalScoredEvent;
import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    private final Size size;

    @Getter
    private final List<DomainEvent> events;

    public Game(Field field, Dot start) {
        this.id = UUID.randomUUID();
        this.fieldId = field.getId();
        this.white = field.getWhite();
        this.black = field.getBlack();
        this.size = new Size(4, 5);
        this.dots = new Dots(size);
        this.path = new Path(dots.getByCoordinates(new Coordinate(0, 0)));
        this.events = new ArrayList<>();
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

        size.lostGoal(coordinate)
                .ifPresent(t -> events.add(new GoalScoredEvent(this.id, t)));
    }


    public LinkedList<Move> getMoves() {
        return path.getMoves();
    }
}
