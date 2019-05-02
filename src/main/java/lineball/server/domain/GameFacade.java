package lineball.server.domain;

import lineball.server.api.DotDto;
import lineball.server.domain.field.FieldFacade;
import lineball.server.domain.game.command.ActionCommand;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class GameFacade {

  private FieldFacade fieldFacade;

  public UUID createField() {
    UUID fieldId = UUID.randomUUID();
    fieldFacade.newField(fieldId);
    return fieldId;
  }

  public void enterField(UUID fieldId, UUID playerId) {
    fieldFacade.enter(fieldId, playerId);
  }

  public void addMovePlayer(UUID fieldId, Player player, DotDto dot) {
    fieldFacade.addMovePlayer(fieldId, player, dot);
  }

  public void revertMovePlayer(UUID fieldId, Player player) {
    fieldFacade.revertMovePlayer(fieldId, player);
  }

  public boolean hasPlayerOn(UUID fieldId, UUID playerId) {
    return fieldFacade.hasPlayerOn(fieldId, playerId);
  }

    public void makeMove(ActionCommand command, UUID playerId) {
        System.out.println(command.getX());
        System.out.println(command.getY());
        System.out.println(command.getType());
    }
}
