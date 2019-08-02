package lineball.server.domain.game

import lineball.server.domain.DomainException

class ChangeTurnSpec extends GameFacadeTemplate {

    def "Turn changes after finishing in dot that was not used before"() {
        given: "White plays(0,1)"
        startGame()
        facade.makeMove(commandMove(0, 1), white)
        when: "White plays(0,2)"
        facade.makeMove(commandMove(0, 2), white)
        then: "Exception thrown - wrong player tour"
        thrown(DomainException)
    }

    def "Player continue their turn after hitting dot that was used before"() {
        given: "Game went (0,0)/(0,1)/(1,1). White to move."
        startGame()
        facade.makeMove(commandMove(0, 1), white)
        facade.makeMove(commandMove(1, 1), black)
        when: "White plays(0,0)"
        facade.makeMove(commandMove(0, 0), white)
        facade.makeMove(commandMove(-1, 0), white)
        then: "It is still white to move"
        noExceptionThrown()
    }

    def "Player continue their turn after hitting sideline"() {
        given: "Game went (0,0)/(1,0)/(2,0)/(3,0). Black to move."
        startGame()
        facade.makeMove(commandMove(1, 0), white)
        facade.makeMove(commandMove(2, 0), black)
        facade.makeMove(commandMove(3, 0), white)
        when: "Black plays(4,0)"
        facade.makeMove(commandMove(4, 0), black)
        facade.makeMove(commandMove(4, 1), black)
        then: "It is still black to move"
        noExceptionThrown()
    }

    def "Player continue their turn after hitting end line"() {
        given: "Game went (0,0)/(1,1)/(2,2)/(3,3)/(3,4). White to move."
        startGame()
        facade.makeMove(commandMove(1, 1), white)
        facade.makeMove(commandMove(2, 2), black)
        facade.makeMove(commandMove(3, 3), white)
        facade.makeMove(commandMove(3, 4), black)
        when: "White plays(3,5)"
        facade.makeMove(commandMove(3, 5), white)
        facade.makeMove(commandMove(2, 5), white)
        then: "It is still white to move"
        noExceptionThrown()
    }
}
