package lineball.server.domain.game;

import lineball.server.domain.field.Field;
import lombok.Getter;

import java.util.UUID;

public class Game {

    @Getter
    private final UUID id;

    private final Field field;

    public Game(Field field) {
        this.id = UUID.randomUUID();
        this.field = field;
    }
}
