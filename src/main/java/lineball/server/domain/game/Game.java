package lineball.server.domain.game;

import lineball.server.domain.DomainEvent;
import lineball.server.domain.game.dot.*;
import lineball.server.domain.game.command.ActionCommand;
import lineball.server.domain.game.events.DeadEndEvent;
import lineball.server.domain.game.events.GoalScoredEvent;
import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Game {

    @Getter
    private final UUID id;
    @Getter
    private final UUID fieldId;
    @Getter
    private final GameStatus status;
    private final Path path;
    private final Dots dots;
    private final Size size;

    @Getter
    private final List<DomainEvent> events;

    public Game(UUID fieldId) {
        this.id = UUID.randomUUID();
        this.fieldId = fieldId;
        this.size = new Size(4, 5);
        this.dots = new Dots(size);
        this.path = new Path(dots.getByCoordinates(new Coordinate(0, 0)));
        this.events = new ArrayList<>();
        this.status = GameStatus.ACTIVE;
    }

    public void move(ActionCommand action, PlayerType type) {
        Coordinate coordinate = new Coordinate(action.getX(), action.getY());

        path.addMove(dots.getByCoordinates(coordinate), type);

        size.lostGoal(coordinate)
                .ifPresent(t -> events.add(new GoalScoredEvent(this.id, t)));

        if (path.getMoves().getLast().getTo().getAvailable() == 0) {
            events.add(new DeadEndEvent(this.id, type.getOpposite()));
        }
    }


    public LinkedList<Move> getMoves() {
        return path.getMoves();
    }
}
