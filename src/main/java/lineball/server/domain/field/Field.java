package lineball.server.domain.field;

import lineball.server.domain.Player;
import lombok.Getter;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class Field {
    @Getter
    private final UUID id;
    private final Player white;
    private final Player black;

    public Field(UUID id, Player white, Player black) {
        this.id = requireNonNull(id);
        this.white = requireNonNull(white);
        this.black = requireNonNull(black);
    }
}
