package lineball.server.domain.game;

import lineball.server.domain.DomainException;
import lineball.server.domain.eventstore.Aggregate;
import lineball.server.domain.eventstore.DomainEvent;
import lineball.server.domain.game.dot.*;
import lineball.server.domain.game.command.ActionCommand;
import lineball.server.domain.game.events.GameEndedEvent;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game implements Aggregate {

    @Getter
    private final UUID id;
    @Getter
    private final UUID fieldId;
    @Getter
    private GameStatus status;
    private final Path path;
    private final Dots dots;

    @Getter
    private final List<DomainEvent> events;

    public Game(UUID fieldId) {
        this.id = UUID.randomUUID();
        this.fieldId = fieldId;
        this.dots = new Dots(new MoveInfo(4, 5));
        this.path = new Path(dots.getByCoordinates(new Coordinate(0, 0)));
        this.events = new ArrayList<>();
        this.status = GameStatus.ACTIVE;
    }

    public void move(ActionCommand action, PlayerType type) {
        Coordinate coordinate = new Coordinate(action.getX(), action.getY());
        path.addMove(dots.getByCoordinates(coordinate), type);
        dots.lostGoal(coordinate)
                .ifPresentOrElse(this::endGame,
                        () -> {
                            if (path.getMoves().getLast().getTo().getAvailable() == 0) {
                                endGame(type.getOpposite());
                            }
                        });
    }

    private void endGame(PlayerType win) {
        if (!status.equals(GameStatus.ACTIVE)) {
            throw new DomainException("bb");
        }
        status = GameStatus.END;
        events.add(new GameEndedEvent(this.id, win));
    }

    @Override
    public void clearPublishedEvents() {
        events.clear();
    }
}
