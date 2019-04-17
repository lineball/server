package lineball.server.domain;

import lineball.server.domain.field.FieldRepository;
import lineball.server.domain.game.command.ActionCommand;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class FieldFacade {

    private final FieldRepository fieldRepository;

    public void makeMove(final ActionCommand command, final UUID playerId) {
        System.out.println(command.getX());
        System.out.println(command.getY());
        System.out.println(command.getType());
    }
}
