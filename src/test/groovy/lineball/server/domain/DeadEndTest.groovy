package lineball.server.domain

import spock.lang.Specification

class DeadEndTest extends Specification {

    def "White looses after hitting corner"() {
        given: "Game went (0,0)/(1,1)/(2,2),(3,3)/(3,4). White to move."
        when: "White plays(4,5)"
        then: "White looses game"
    }

    def "Black looses after scoring sideline dead end"() {
        given: "Game went (0,0)/(1,0)/(2,0)/(3,0)/(4,1),(3,2)/(3,1). Black to move."
        when: "Black plays(4,1)"
        then: "Black looses game (no more legal moves possible)"
    }

    def "Black looses after scoring central dot dead end"() {
        given: "Game went (0,0)/(0,1)/(1,1)/(0,0),(1,0)/(-1,1)/(0,0),(0,-1)/(-1,-1)/(0,0),(-1,0)/(-1,1). White to move."
        when: "White plays(0,0)"
        then: "White looses game (no more legal moves possible)"
    }

}
