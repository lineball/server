package lineball.server.domain

import lineball.server.domain.dot.Dot
import lineball.server.domain.field.Field
import spock.lang.Specification

class DeadEndTest extends Specification {

    UUID uuidField = UUID.randomUUID()
    UUID uuidPlayer1 = UUID.randomUUID()
    UUID uuidPlayer2 = UUID.randomUUID()
    Player player1 = new Player(uuidPlayer1)
    Player player2 = new Player(uuidPlayer2)
    Field field = new Field(uuidField)


    def "White looses after hitting corner"() {
        given: "Game went (0,0)/(1,1)/(2,2),(3,3)/(3,4). White to move."
        field.enter(player1)
        field.enter(player2)
        field.startGame(player1.getId())
        field.addMovePlayer(player1, new Dot(1,1))
        field.addMovePlayer(player2, new Dot(2,2))
        field.addMovePlayer(player1, new Dot(3,3))
        field.addMovePlayer(player2, new Dot(3,4))
        when: "White plays(4,5)"
        field.addMovePlayer(player1, new Dot(4,5))
        then: "White looses game"
        def domainException = thrown(DomainException)
        domainException.message == 'Player: ' + player1.getId() + ' looses game'
    }

    def "Black looses after scoring sideline dead end"() {
        given: "Game went (0,0)/(1,0)/(2,0)/(3,0)/(4,1),(3,2)/(3,1). Black to move."
        field.enter(player1)
        field.enter(player2)
        field.startGame(player1.getId())
        field.addMovePlayer(player1, new Dot(1,0))
        field.addMovePlayer(player2, new Dot(2,0))
        field.addMovePlayer(player1, new Dot(3,0))
        field.addMovePlayer(player2, new Dot(4,1))
        field.addMovePlayer(player2, new Dot(3,2))
        field.addMovePlayer(player1, new Dot(3,1))
        when: "Black plays(4,1)"
        field.addMovePlayer(player2, new Dot(4,1))
        then: "Black looses game (no more legal moves possible)"
        def domainException = thrown(DomainException)
        domainException.message == 'Player: ' + player2.getId() + ' looses game'
    }

    def "Black looses after scoring central dot dead end"() {
        given: "Game went (0,0)/(0,1)/(1,1)/(0,0),(1,0)/(1,-1)/(0,0),(0,-1)/(-1,-1)/(0,0),(-1,0)/(-1,1). White to move."
        field.enter(player1)
        field.enter(player2)
        field.startGame(player1.getId())
        field.addMovePlayer(player1, new Dot(0,1))
        field.addMovePlayer(player2, new Dot(1,1))
        field.addMovePlayer(player1, new Dot(0,0))
        field.addMovePlayer(player1, new Dot(1,0))
        field.addMovePlayer(player2, new Dot(1,-1))
        field.addMovePlayer(player1, new Dot(0,0))
        field.addMovePlayer(player1, new Dot(0,-1))
        field.addMovePlayer(player2, new Dot(-1,-1))
        field.addMovePlayer(player1, new Dot(0,0))
        field.addMovePlayer(player1, new Dot(-1,0))
        field.addMovePlayer(player2, new Dot(-1,1))
        when: "White plays(0,0)"
        field.addMovePlayer(player1, new Dot(0,0))
        then: "White looses game (no more legal moves possible)"
        def domainException = thrown(DomainException)
        domainException.message == 'Player: ' + player1.getId() + ' looses game'
    }

}
