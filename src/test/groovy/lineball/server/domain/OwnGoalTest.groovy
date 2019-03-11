package lineball.server.domain

import spock.lang.Specification

class OwnGoalTest extends Specification {

    def "White looses after scoring own goal"() {
        given: "Game went (0,0)/(0,-1)/(0,-2)/(0,-3)/(0,-4). White to move."
        when: "White plays(1,-5),(0,-6)"
        then: "White looses game"
    }

    def "Black looses after scoring own goal"() {
        given: "Game went (0,0)/(0,1)/(0,2)/(0,3)/(0,4)/(0,5). Black to move."
        when: "Black plays(0,6)"
        then: "Black looses game"
    }

}
