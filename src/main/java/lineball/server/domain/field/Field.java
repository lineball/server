package lineball.server.domain.field;

import io.vavr.control.Option;
import lineball.server.domain.DomainException;
import lineball.server.domain.Player;
import lombok.Getter;

import java.util.UUID;
import java.util.function.Predicate;

import static io.vavr.control.Option.none;
import static io.vavr.control.Option.some;

public class Field {
    @Getter
    private final UUID id;
    private final Option<Player> white;
    private final Option<Player> black;

    public Field(UUID id) {
        this.id = id;
        white = none();
        black = none();
    }

    private Field(UUID id, Option<Player> white, Option<Player> black) {
        this.id = id;
        this.white = white;
        this.black = black;
    }

    public Field enter(final Player player) {
        if(white.isDefined() && black.isDefined())
            throw new DomainException("Cannot enter field if there are already two players");

        if(white.isEmpty())
            return new Field(this.id, some(player), none());

        return new Field(this.id, white, some(player));
    }

    public Field readyToStart(UUID playerId) {
        var maybeWhite = white.filter(isPlayer(playerId)).map(Player::readyToPlay);
        var maybeBlack = black.filter(isPlayer(playerId)).map(Player::readyToPlay);
        return new Field(this.id, maybeWhite.orElse(white) , maybeBlack.orElse(black));
    }

    public Field startGame(UUID playerId) {
        var field = readyToStart(playerId);
        return field.startGame();
    }

    public boolean hasPlayerOn(UUID playerId) {
        return white.exists(isPlayer(playerId)) || black.exists(isPlayer(playerId));
    }

    private Field startGame() {
        if( !white.exists(Player::isReady) || !black.exists(Player::isReady))
            throw new DomainException("Cannot init game without second player ready");

        return this;
    }

    private Predicate<Player> isPlayer(UUID playerId) {
        return player -> player.is(playerId);
    }
}
