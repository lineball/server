package lineball.server.domain

import spock.lang.Specification

class ChangeTurnTest extends Specification {

    def "Turn changes after finishing in dot that was not used before"() {
        when: "White plays(1,0)"
        then: "It is black to move"
    }

    def "Player continue their turn after hitting dot that was used before"() {
        given: "Game went (0,0)/(0,1)/(1,1). White to move."
        when: "White plays(0,0)"
        then: "It is still white to move"
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
