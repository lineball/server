package lineball.server.domain.dot

import org.junit.Assert
import spock.lang.Specification

class DotTest extends Specification {

    def "shouldNotAccessToDotWithMoreThan2moves"() {
        given:

        Dot from = new Dot(0,0)
        Dot to = new Dot(1,2)

        when:
        def isAccessible = from.isAccessible(to)

        then:
        Assert.assertFalse(isAccessible)
    }

    def "shouldAccessToDotWithOneMove"() {
        given:
        Dot from = new Dot(0,0)
        Dot to = new Dot(-1,-1)

        when:
        def isAccessible = from.isAccessible(to)

        then:
        Assert.assertTrue(isAccessible)
    }

    def "shouldNotMoveBetween2BorderDots"() {
        given:
        Dot from = new Dot(4,0)
        Dot to = new Dot(4,-1)

        when:
        def isAccessible = from.isAccessible(to)

        then:
        Assert.assertFalse(isAccessible)
    }

    def "shouldNotMoveOutsideBorder"() {
        given:
        Dot from = new Dot(4,0)
        Dot to = new Dot(5,0)

        when:
        def isAccessible = from.isAccessible(to)

        then:
        Assert.assertFalse(isAccessible)
    }
}
