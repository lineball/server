package lineball.server.domain.game

import lineball.server.domain.EventStoreRepository
import lineball.server.domain.game.events.DeadEndEvent
import spock.lang.Specification

class DeadEndTest extends Specification implements SampleBuilder {
    GameConfiguration configuration = new GameConfiguration()
    GameFacade facade = configuration.gameFacade()
    EventStoreRepository eventStoreRepository = configuration.getEventStoreRepository()

    def "White looses after hitting corner"() {
        given: "Game went (0,0)/(1,1)/(2,2),(3,3)/(3,4). White to move."
        facade.makeMove(commandMove(1, 1), white)
        facade.makeMove(commandMove(2, 2), black)
        facade.makeMove(commandMove(3, 3), white)
        facade.makeMove(commandMove(3, 4), black)
        when: "White plays(4,5)"
        facade.makeMove(commandMove(4, 5), white)
        then: "Black win game"
        Optional<DeadEndEvent> goal = eventStoreRepository.findAll().stream()
                .filter({ e -> e.eventType == DeadEndEvent.type })
                .findFirst() as Optional<DeadEndEvent>
        goal.isPresent()
        goal.get().playerWin == PlayerType.BLACK
    }

    def "Black looses after scoring sideline dead end"() {
        given: "Game went (0,0)/(1,0)/(2,0)/(3,0)/(4,1),(3,2)/(3,1). Black to move."
        facade.makeMove(commandMove(1, 0), white)
        facade.makeMove(commandMove(2, 0), black)
        facade.makeMove(commandMove(3, 0), white)
        facade.makeMove(commandMove(4, 1), black)
        facade.makeMove(commandMove(3, 2), black)
        facade.makeMove(commandMove(3, 1), white)
        when: "Black plays(4,1)"
        facade.makeMove(commandMove(4, 1), black)
        then: "Black looses game (no more legal moves possible)"
        Optional<DeadEndEvent> goal = eventStoreRepository.findAll().stream()
                .filter({ e -> e.eventType == DeadEndEvent.type })
                .findFirst() as Optional<DeadEndEvent>
        goal.isPresent()
        goal.get().playerWin == PlayerType.WHITE
    }

    def "Black looses after scoring central dot dead end"() {
        given: "Game went (0,0)/(0,1)/(1,1)/(0,0),(1,0)/(-1,1)/(0,0),(0,-1)/(-1,-1)/(0,0),(-1,0)/(-1,1). White to move."
        when: "White plays(0,0)"
        then: "White looses game (no more legal moves possible)"
    }

}
