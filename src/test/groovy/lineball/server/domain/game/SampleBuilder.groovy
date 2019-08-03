package lineball.server.domain.game

import groovy.transform.CompileStatic
import lineball.server.domain.dto.FieldDto
import lineball.server.domain.game.command.ActionCommand
import lineball.server.domain.game.command.ActionType
import lineball.server.domain.game.events.GameEndedEvent

@CompileStatic
trait SampleBuilder {

    UUID white = UUID.fromString('1bc9d166-83a7-4960-b387-069e6ecac1e7')
    UUID black = UUID.fromString('85777b10-da4a-42da-82c0-a6edc723f540')

    static ActionCommand commandMove(int x, int y) {
        return ActionCommand.builder().type(ActionType.MOVE).x(x).y(y).build()
    }

    static FieldDto fieldDto() {
        return new FieldDto(UUID.fromString('e997371c-8295-49db-a45b-a67895da40f4'),
                UUID.fromString('1bc9d166-83a7-4960-b387-069e6ecac1e7'),
                UUID.fromString('85777b10-da4a-42da-82c0-a6edc723f540'))
    }
}