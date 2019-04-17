package lineball.server.domain

import lineball.server.persistence.memory.FieldInMemoryRepository
import lineball.server.persistence.memory.GameInMemoryRepository
import spock.lang.Specification

class InitGameTest extends Specification {

    GameFacade gameFacade = setUpFieldFacade()

    def "Game starts with two players"() {
        given: "New game is created"
        def gameId = UUID.randomUUID()
        gameFacade.createGame(gameId)

        and: "first and second player enter the game"
        def whiteId = UUID.randomUUID()
        def blackId = UUID.randomUUID()

        gameFacade.enter(gameId, whiteId)
        gameFacade.enter(gameId, blackId)

        and: "first player (white) init game"
        gameFacade.readyToPlay(gameId, whiteId)

        when: "second player can start game and field to play is created"
        def field = gameFacade.startGame(gameId, blackId)

        then: "field contains white and black player"
        assert field.white.is(whiteId)
        assert field.black.is(blackId)
    }

    def "Single player cannot start game"() {
        given: "new game is created"
        def gameId = UUID.randomUUID()
        gameFacade.createGame(gameId)

        and: "first player enters the game"
        def whiteId = UUID.randomUUID()
        gameFacade.enter(gameId, whiteId)

        when: "first player (white) init game"
        gameFacade.startGame(gameId, whiteId)

        then: "an exception is thrown - cannot init game without second player ready"
        thrown DomainException
    }

    def "Players must be ready to play to start the game"() {
        given: "new game is created"
        def gameId = UUID.randomUUID()
        gameFacade.createGame(gameId)

        and: "first and second player enter the game"
        def whiteId = UUID.randomUUID()
        def blackId = UUID.randomUUID()

        gameFacade.enter(gameId, whiteId)
        gameFacade.enter(gameId, blackId)

        and: "first player (white) is ready to play"
        gameFacade.readyToPlay(gameId, whiteId)

        when: "first player (white) init game"
        gameFacade.startGame(gameId, whiteId)

        then: "an exception is thrown - cannot init game without second player ready"
        thrown DomainException
    }

    def setUpFieldFacade() {
        def fieldRepo = new FieldInMemoryRepository()
        def gameRepo = new GameInMemoryRepository()

        return new GameFacade(fieldRepo, gameRepo)
    }

}
