package lineball.server.domain;

import lineball.server.domain.game.command.ActionCommand;

import java.util.UUID;

public class GameFacade {

    public void makeMove(ActionCommand command, UUID playerId) {
        System.out.println(command.getX());
        System.out.println(command.getY());
        System.out.println(command.getType());
    }
}
