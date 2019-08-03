package lineball.server.domain.field

import lineball.server.domain.DomainException
import spock.lang.Specification

class InitGameTest extends Specification {
    FieldFacade fieldFacade = new FieldConfiguration().fieldFacade()

    def "Game starts with two players"() {
        given: "New field is created"
        def fieldId = UUID.randomUUID()
        fieldFacade.newField(fieldId)

        when: "first and second player enter the field"
        def whiteId = UUID.randomUUID()
        def blackId = UUID.randomUUID()

        fieldFacade.enter(fieldId, whiteId)
        fieldFacade.enter(fieldId, blackId)

        then: "field is ready to play on it"
        fieldFacade.readyToPlay(fieldId)
    }

    def "Single player cannot start game"() {
        given: "new field is created"
        def fieldId = UUID.randomUUID()
        fieldFacade.newField(fieldId)

        when: "first player enters the field"
        def whiteId = UUID.randomUUID()
        fieldFacade.enter(fieldId, whiteId)

        then: "field is not ready to play on it"
        !fieldFacade.readyToPlay(fieldId)
    }
}
