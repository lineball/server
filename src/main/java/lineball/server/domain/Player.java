package lineball.server.domain;

import lombok.Getter;

import java.util.UUID;

public class Player {

    @Getter
    private UUID id;

    public Player(UUID id) {
        this.id = id;
    }
}
