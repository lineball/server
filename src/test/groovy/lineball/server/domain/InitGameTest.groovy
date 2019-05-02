package lineball.server.domain

import lineball.server.domain.field.FieldFacade
import lineball.server.persistence.memory.FieldInMemoryRepository
import spock.lang.Specification

class InitGameTest extends Specification {
    FieldFacade fieldFacade = setUpFieldFacade()

    def "Game starts with two players"() {
        given: "New field is created"
        def fieldId = UUID.randomUUID()
        fieldFacade.newField(fieldId)

        and: "first and second player enter the field"
        def whiteId = UUID.randomUUID()
        def blackId = UUID.randomUUID()

        fieldFacade.enter(fieldId, whiteId)
        fieldFacade.enter(fieldId, blackId)

        when: "first player (white) init game"
        fieldFacade.readyToPlay(whiteId)

        then: "second player can start game"
        notThrown(fieldFacade.startGame(blackId))
    }

    def "Single player cannot start game"() {
        given: "new field is created"
        def fieldId = UUID.randomUUID()
        fieldFacade.newField(fieldId)

        and: "first player enters the field"
        def whiteId = UUID.randomUUID()
        fieldFacade.enter(fieldId, whiteId)

        when: "first player (white) init game"
        fieldFacade.startGame(whiteId)

        then: "an exception is thrown - cannot init game without second player"
        thrown DomainException
    }

    def setUpFieldFacade() {
        def fieldRepo = new FieldInMemoryRepository()

        return new FieldFacade(fieldRepo)
    }

}
