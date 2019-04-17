package lineball.server.domain.game;

import io.vavr.control.Option;
import lineball.server.domain.DomainException;
import lineball.server.domain.Player;
import lineball.server.domain.field.Field;
import lombok.Getter;

import java.util.UUID;
import java.util.function.Predicate;

import static io.vavr.control.Option.none;
import static io.vavr.control.Option.some;

public class Game {

    @Getter
    private final UUID id;
    private final Option<Player> white;
    private final Option<Player> black;

    public Game(final UUID id) {
        this(id, none(), none());
    }

    public Game(UUID id, Option<Player> white, Option<Player> black) {
        this.id = id;
        this.white = white;
        this.black = black;
    }

    public Game enter(final Player player) {
        if (white.isDefined() && black.isDefined())
            throw new DomainException("Cannot enter game if there are already two players");

        return white.isEmpty() ? new Game(this.id, some(player), none()) : new Game(this.id, white, some(player));
    }

    public Game readyToStart(final UUID playerId) {
        var maybeWhite = white.filter(isPlayer(playerId)).map(Player::readyToPlay);
        var maybeBlack = black.filter(isPlayer(playerId)).map(Player::readyToPlay);
        return new Game(this.id, maybeWhite.orElse(white), maybeBlack.orElse(black));
    }

    public Field startGame(final UUID playerId) {
        var game = readyToStart(playerId);
        return game.startGame();
    }

    private Field startGame() {
        if (!white.exists(Player::isReady) || !black.exists(Player::isReady))
            throw new DomainException("Cannot init game without second player ready");

        return new Field(UUID.randomUUID(),
                white.getOrElseThrow(() -> new DomainException("Cannot init game without second player")),
                black.getOrElseThrow(() -> new DomainException("Cannot init game without second player"))
        );
    }

    private Predicate<Player> isPlayer(final UUID playerId) {
        return player -> player.is(playerId);
    }
}
