package lineball.server.domain.game

import lineball.server.domain.EventStoreRepository
import lineball.server.domain.game.events.GoalScoredEvent
import spock.lang.Specification


class GoalSpec extends Specification implements SampleBuilder {

    GameConfiguration configuration = new GameConfiguration()
    GameFacade facade = configuration.gameFacade()
    EventStoreRepository eventStoreRepository = configuration.getEventStoreRepository()

    def "White wins after scoring goal"() {
        given: "Game went (0,0)/(0,1)/(0,2)/(0,3)/(0,4). White to move."
        facade.makeMove(commandMove(0, 1), white)
        facade.makeMove(commandMove(0, 2), black)
        facade.makeMove(commandMove(0, 3), white)
        facade.makeMove(commandMove(0, 4), black)
        when: "White plays(1,5),(0,6)"
        facade.makeMove(commandMove(1, 5), white)
        facade.makeMove(commandMove(0, 6), white)
        then: "White wins game"
        Optional<GoalScoredEvent> goal = eventStoreRepository.findAll().stream()
                .filter({ e -> e.eventType == GoalScoredEvent.type })
                .findFirst() as Optional<GoalScoredEvent>
        goal.isPresent()
        goal.get().goalForPlayer == PlayerType.WHITE
    }

    def "Black wins after scoring goal"() {
        given: "Game went (0,0)/(0,-1)/(0,-2)/(0,-3)/(0,-4)/(0,-5). Black to move."
        facade.makeMove(commandMove(0, -1), white)
        facade.makeMove(commandMove(0, -2), black)
        facade.makeMove(commandMove(0, -3), white)
        facade.makeMove(commandMove(0, -4), black)
        facade.makeMove(commandMove(0, -5), white)
        when: "Black plays(0,-6)"
        facade.makeMove(commandMove(0, -6), black)
        then: "Black wins game"
        Optional<GoalScoredEvent> goal = eventStoreRepository.findAll().stream()
                .filter({ e -> e.eventType == GoalScoredEvent.type })
                .findFirst() as Optional<GoalScoredEvent>
        goal.isPresent()
        goal.get().goalForPlayer == PlayerType.BLACK
    }

}
