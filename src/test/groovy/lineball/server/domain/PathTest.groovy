package lineball.server.domain

import lineball.server.domain.dot.Dot
import spock.lang.Specification

class PathTest extends Specification {

    Path path = new Path()
    def "Add exist dot in path"() {
        given:

        def dot = new Dot(0,0)

        when:
        path.addDot(dot)

        then:
        thrown(DomainException)
    }

    def "Add dot to path"() {
        given:

        def dot = new Dot(1,1)

        when:
        path.addDot(dot)

        then:
        path.getDots().size() == 2
    }

    def "Add dot to path which is to far"() {
        given:

        def dot = new Dot(3,1)

        when:
        path.addDot(dot)

        then:
        thrown(DomainException)
    }

    def "RemoveDot"() {
        given:
        def dot = new Dot(1,1)
        when:
        path.addDot(dot)
        path.removeDot()

        then:
        path.getDots().size() == 1
    }
}
