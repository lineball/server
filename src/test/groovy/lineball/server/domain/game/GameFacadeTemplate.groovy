package lineball.server.domain.game

import lineball.server.domain.eventstore.EventPublisher
import lineball.server.domain.field.FieldFacade
import spock.lang.Specification

class GameFacadeTemplate extends Specification implements SampleBuilder {
    FieldFacade fieldFacade = Stub(FieldFacade)
    EventPublisher eventPublisher = Mock(EventPublisher)
    GameFacade facade = new GameConfiguration().gameFacade(fieldFacade, eventPublisher)

    def startGame() {
        fieldFacade.fieldByPlayerId(_) >> fieldDto()
        fieldFacade.readyToPlay(_) >> true
        facade.startGame(white)
    }
}
