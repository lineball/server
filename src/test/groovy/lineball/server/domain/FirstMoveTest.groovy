package lineball.server.domain

import lineball.server.domain.dot.Dot
import lineball.server.domain.field.Field
import lineball.server.domain.game.Game
import spock.lang.Specification

class FirstMoveTest extends Specification {
    def "First player makes move - direction 1"() {
        given: "Players accepts game initiation"
        UUID uuidField = UUID.randomUUID()
        UUID uuidPlayer1 = UUID.randomUUID()
        UUID uuidPlayer2 = UUID.randomUUID()
        Player player1 = new Player(uuidPlayer1)
        Player player2 = new Player(uuidPlayer2)
        Field field = new Field(uuidField)
        field.enter(player1)
        field.enter(player2)
        field.startGame()
        def initDot = new Dot(1,1)
        and: "game starts"
        when: "first player make move - direction 1"
        player1.move(initDot, field.getPath())
        then: "current game position changes to ..."
        field.getPath().getDots().last() == initDot
    }
}
