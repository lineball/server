package lineball.server.domain.game

import lineball.server.domain.DomainException

class FirstMoveSpec extends GameFacadeTemplate {

    def "Player cannot move two dots away"() {
        given: "White plays(0,1)"
        startGame()
        facade.makeMove(commandMove(0, 1), white)
        when: "Black plays(0,3)"
        facade.makeMove(commandMove(0, 3), black)
        then: "Exception thrown - wrong move"
        thrown(DomainException)
    }

}
