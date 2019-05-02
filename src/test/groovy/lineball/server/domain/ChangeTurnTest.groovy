package lineball.server.domain

import lineball.server.domain.dot.Dot
import lineball.server.domain.field.Field
import spock.lang.Specification

class ChangeTurnTest extends Specification {

    UUID uuidField = UUID.randomUUID()
    UUID uuidPlayer1 = UUID.randomUUID()
    UUID uuidPlayer2 = UUID.randomUUID()
    Player player1 = new Player(uuidPlayer1)
    Player player2 = new Player(uuidPlayer2)
    Field field = new Field(uuidField)

    def "Turn changes after finishing in dot that was not used before"() {
        given: "New game started"
        field.enter(player1)
        field.enter(player2)
        field.startGame()
        when: "White plays(1,0)"
        field.addMovePlayer(player1, new Dot(1,0))
        then: "It is black to move"
        field.currentPlayer == player2
        field.currentPlayer != player1
    }

    def "Player continue their turn after hitting dot that was used before"() {
        given: "Game went (0,0)/(0,1)/(1,1). White to move."
        field.enter(player1)
        field.enter(player2)
        field.startGame()
        field.addMovePlayer(player1, new Dot(0,1))
        field.addMovePlayer(player2, new Dot(1,1))
        when: "White plays(0,0)"
        field.addMovePlayer(player1, new Dot(0,0))
        then: "It is still white to move"
        field.currentPlayer != player2
        field.currentPlayer == player1
    }

    def "Player continue their turn after hitting sideline"() {
        given: "Game went (0,0)/(1,0)/(2,0)/(3,0). Black to move."
        field.enter(player1)
        field.enter(player2)
        field.startGame()
        field.addMovePlayer(player1, new Dot(1,0))
        field.addMovePlayer(player2, new Dot(2,0))
        field.addMovePlayer(player1, new Dot(3,0))
        when: "Black plays(4,0)"
        field.addMovePlayer(player2, new Dot(4,0))
        then: "It is still black to move"
        field.currentPlayer == player2
        field.currentPlayer != player1
    }

    def "Player continue their turn after hitting endline"() {
        given: "Game went (0,0)/(1,1)/(2,2)/(3,3)/(3,4). White to move."
        field.enter(player1)
        field.enter(player2)
        field.startGame()
        field.addMovePlayer(player1, new Dot(1,1))
        field.addMovePlayer(player2, new Dot(2,2))
        field.addMovePlayer(player1, new Dot(3,3))
        field.addMovePlayer(player2, new Dot(3,4))
        when: "White plays(3,5)"
        field.addMovePlayer(player1, new Dot(3,5))
        then: "It is still white to move"
        field.currentPlayer != player2
        field.currentPlayer == player1
    }

}
