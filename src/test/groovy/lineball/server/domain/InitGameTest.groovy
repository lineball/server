package lineball.server.domain

import spock.lang.Specification

class InitGameTest extends Specification {
    def "Game starts with two players"() {
        given: "New field is created"
        and: "first and second players enters the field"
        when: "first player (white) init game"
        then: "second player got event and can start game"
    }

    def "Single player cannot start game"() {
        given: "new field is created"
        and: "first player enters the field"
        when: "first player (white) init game"
        then: "an exception is thrown - cannot init game without second player"
    }

}
