package lineball.server.domain.game


class DeadEndTest extends GameFacadeTemplate {

    def "White looses after hitting corner"() {
        given: "Game went (0,0)/(1,1)/(2,2),(3,3)/(3,4). White to move."
        startGame()
        facade.makeMove(commandMove(1, 1), white)
        facade.makeMove(commandMove(2, 2), black)
        facade.makeMove(commandMove(3, 3), white)
        facade.makeMove(commandMove(3, 4), black)
        when: "White plays(4,5)"
        facade.makeMove(commandMove(4, 5), white)
        then: "Black win game"
        1 * eventPublisher.publish(_, { it ->
            it.size() == 1 &&
                    it.get(0).eventType == 'game.ended' &&
                    it.get(0).playerWin == PlayerType.BLACK
        })
    }

    def "Black looses after scoring sideline dead end"() {
        given: "Game went (0,0)/(1,0)/(2,0)/(3,0)/(4,1),(3,2)/(3,1). Black to move."
        startGame()
        facade.makeMove(commandMove(1, 0), white)
        facade.makeMove(commandMove(2, 0), black)
        facade.makeMove(commandMove(3, 0), white)
        facade.makeMove(commandMove(4, 1), black)
        facade.makeMove(commandMove(3, 2), black)
        facade.makeMove(commandMove(3, 1), white)
        when: "Black plays(4,1)"
        facade.makeMove(commandMove(4, 1), black)
        then: "Black looses game (no more legal moves possible)"
        1 * eventPublisher.publish(_, { it ->
            it.size() == 1 &&
                    it.get(0).eventType == 'game.ended' &&
                    it.get(0).playerWin == PlayerType.WHITE
        })
    }

    def "Black looses after scoring central dot dead end"() {
        given: "Game went (0,0)/(0,1)/(1,1)/(0,0),(1,0)/(-1,1)/(0,0),(0,-1)/(-1,-1)/(0,0),(-1,0)/(-1,1). White to move."
        when: "White plays(0,0)"
        then: "White looses game (no more legal moves possible)"
    }

}
