package lineball.server.domain

import spock.lang.Specification

class ReverseMoveTest extends Specification {

    def "Player can reverse their own move"() {
        given: "Game went (0,0)/(0,1)/(1,1). White to move."
        when: "White plays(0,0)"
        then: "White can reverse last move"
    }

    def "Player cannot reverse opponent move"() {
        given: "Game went (0,0)/(0,1). Black to move."
        when: "Black wants to reverse move"
        then: "Move cannot be reversed"
    }

}
