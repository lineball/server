package lineball.server.domain.game

import lineball.server.domain.DomainException
import lineball.server.domain.GameConfiguration
import lineball.server.domain.game.GameFacade
import lineball.server.domain.game.SampleBuilder
import spock.lang.Specification

class ChangeTurnSpec extends Specification implements SampleBuilder {

    GameFacade facade = new GameConfiguration().gameFacade();

    def "Turn changes after finishing in dot that was not used before"() {
        given: "White plays(0,1)"
        facade.makeMove(commandMove(0, 1), playerId1)
        when: "White plays(0,2)"
        facade.makeMove(commandMove(0, 2), playerId1)
        then: "Exception thrown - wrong player tour"
        thrown(DomainException)
    }

    def "Player continue their turn after hitting dot that was used before"() {
        given: "Game went (0,0)/(0,1)/(1,1). White to move."
        facade.makeMove(commandMove(0, 1), playerId1)
        facade.makeMove(commandMove(1, 1), playerId2)
        when: "White plays(0,0)"
        facade.makeMove(commandMove(0, 0), playerId1)
        facade.makeMove(commandMove(-1, 0), playerId1)
        then: "It is still white to move"
        noExceptionThrown()
    }

    def "Player continue their turn after hitting sideline"() {
        given: "Game went (0,0)/(1,0)/(2,0)/(3,0). Black to move."
        when: "Black plays(4,0)"
        then: "It is still black to move"
    }

    def "Player continue their turn after hitting endline"() {
        given: "Game went (0,0)/(1,1)/(2,2)/(3,3)/(3,4). White to move."
        when: "White plays(3,5)"
        then: "It is still white to move"
    }

}
