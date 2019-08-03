package lineball.server.domain.game

class OwnGoaSpec extends GameFacadeTemplate {

    def "White looses after scoring own goal"() {
        given: "Game went (0,0)/(0,-1)/(0,-2)/(0,-3)/(0,-4). White to move."
        startGame()
        facade.makeMove(commandMove(0, -1), white)
        facade.makeMove(commandMove(0, -2), black)
        facade.makeMove(commandMove(0, -3), white)
        facade.makeMove(commandMove(0, -4), black)
        when: "White plays(1,-5),(0,-6)"
        facade.makeMove(commandMove(1, -5), white)
        facade.makeMove(commandMove(0, -6), white)
        then: "Black wins game"
        1 * eventPublisher.publish(_, { it ->
            it.size() == 1 &&
                    it.get(0).eventType == 'game.ended' &&
                    it.get(0).playerWin == PlayerType.BLACK
        })
    }

    def "Black looses after scoring own goal"() {
        given: "Game went (0,0)/(0,1)/(0,2)/(0,3)/(0,4)/(0,5). Black to move."
        startGame()
        facade.makeMove(commandMove(0, 1), white)
        facade.makeMove(commandMove(0, 2), black)
        facade.makeMove(commandMove(0, 3), white)
        facade.makeMove(commandMove(0, 4), black)
        facade.makeMove(commandMove(0, 5), white)
        when: "Black plays(0,6)"
        facade.makeMove(commandMove(0, 6), black)
        then: "White wins game"
        1 * eventPublisher.publish(_, { it ->
            it.size() == 1 &&
                    it.get(0).eventType == 'game.ended' &&
                    it.get(0).playerWin == PlayerType.WHITE
        })
    }

}
