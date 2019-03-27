package lineball.server.domain

import lineball.server.domain.dot.Dot
import lineball.server.domain.field.Field
import spock.lang.Specification

class OwnGoalTest extends Specification {

    UUID uuidField = UUID.randomUUID()
    UUID uuidPlayer1 = UUID.randomUUID()
    UUID uuidPlayer2 = UUID.randomUUID()
    Player player1 = new Player(uuidPlayer1)
    Player player2 = new Player(uuidPlayer2)
    Field field = new Field(uuidField)

    def "White looses after scoring own goal"() {
        given: "Game went (0,0)/(0,-1)/(0,-2)/(0,-3)/(0,-4). White to move."
        field.enter(player1)
        field.enter(player2)
        field.startGame(player1.getId())
        field.addMovePlayer(player1, new Dot(0,-1))
        field.addMovePlayer(player2, new Dot(0,-2))
        field.addMovePlayer(player1, new Dot(0,-3))
        field.addMovePlayer(player2, new Dot(0,-4))
        when: "White plays(1,-5),(0,-6)"
        field.addMovePlayer(player1, new Dot(1,-5))
        field.addMovePlayer(player1, new Dot(0,-6))
        then: "White looses game"
        def exception = thrown(DomainException)
        exception.message == 'End game'
    }

    def "Black looses after scoring own goal"() {
        given: "Game went (0,0)/(0,1)/(0,2)/(0,3)/(0,4)/(0,5). Black to move."
        field.enter(player1)
        field.enter(player2)
        field.startGame(player1.getId())
        field.addMovePlayer(player1, new Dot(0,1))
        field.addMovePlayer(player2, new Dot(0,2))
        field.addMovePlayer(player1, new Dot(0,3))
        field.addMovePlayer(player2, new Dot(0,4))
        field.addMovePlayer(player1, new Dot(0,5))
        when: "Black plays(0,6)"
        field.addMovePlayer(player2, new Dot(0,6))
        then: "Black looses game"
        def exception = thrown(DomainException)
        exception.message == 'End game'
    }

}
