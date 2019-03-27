package lineball.server.domain

import lineball.server.domain.dot.Dot
import lineball.server.domain.field.Field
import spock.lang.Specification

class ReverseMoveTest extends Specification {

    UUID uuidField = UUID.randomUUID()
    UUID uuidPlayer1 = UUID.randomUUID()
    UUID uuidPlayer2 = UUID.randomUUID()
    Player player1 = new Player(uuidPlayer1)
    Player player2 = new Player(uuidPlayer2)
    Field field = new Field(uuidField)

    def "Player can reverse their own move"() {
        given: "Game went (0,0)/(0,1)/(1,1). White to move."
        field.enter(player1)
        field.enter(player2)
        field.startGame(player1.getId())
        field.addMovePlayer(player1, new Dot(0,1))
        field.addMovePlayer(player2, new Dot(1,1))
        when: "White plays(0,0)"
        field.addMovePlayer(player1, new Dot(0,0))
        then: "White can reverse last move"
        field.revertMovePlayer(player1)
        noExceptionThrown()
        field.getWhoCanMove().get(player1.getId())
        !field.getWhoCanMove().get(player2.getId())
    }

    def "Player cannot reverse opponent move"() {
        given: "Game went (0,0)/(0,1). Black to move."
        field.enter(player1)
        field.enter(player2)
        field.startGame(player1.getId())
        field.addMovePlayer(player1, new Dot(0,1))
        when: "Black wants to reverse move"
        field.revertMovePlayer(player2)
        then: "Move cannot be reversed"
        thrown(DomainException)
    }

}
