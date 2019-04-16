package lineball.server.domain;

import java.util.UUID;

public class Player {

    private final UUID id;
    private final boolean readyToPlay;

    Player(final UUID id) {
        this(id,false);
    }

    private Player(final UUID id,final boolean readyToPlay) {
        this.id = id;
        this.readyToPlay = readyToPlay;
    }

    public boolean is(UUID id) {
       return this.id.equals(id);
    }

    public Player readyToPlay() {
        return new Player(this.id, true);
    }

    public boolean isReady() {
        return readyToPlay;
    }
}
