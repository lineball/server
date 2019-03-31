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

    def "Simple success path"() {
        given:

        def dot = new Dot(0,1)
        def dot2 = new Dot(0,2)
        def dot3 = new Dot(0,3)
        def dot4 = new Dot(0,4)
        def dot5 = new Dot(0,5)
        def dot6 = new Dot(0,6)

        when:
        path.addDot(dot)
        path.addDot(dot2)
        path.addDot(dot3)
        path.addDot(dot4)
        path.addDot(dot5)
        path.addDot(dot6)

        then:
        def domainException = thrown(DomainException)
        domainException.message == 'won game'
    }

    def "Simple success path down"() {
        given:

        def dot = new Dot(0,-1)
        def dot2 = new Dot(0,-2)
        def dot3 = new Dot(0,-3)
        def dot4 = new Dot(0,-4)
        def dot5 = new Dot(0,-5)
        def dot6 = new Dot(0,-6)

        when:
        path.addDot(dot)
        path.addDot(dot2)
        path.addDot(dot3)
        path.addDot(dot4)
        path.addDot(dot5)
        path.addDot(dot6)

        then:
        def domainException = thrown(DomainException)
        domainException.message == 'won game'
    }

    def "Cannot move to dot in path"() {
        given:

        def dot = new Dot(0,1)
        def dot2 = new Dot(0,2)
        def dot3 = new Dot(1,2)
        def dot4 = new Dot(0,1)
        def dot5 = new Dot(0,0)

        when:
        path.addDot(dot)
        path.addDot(dot2)
        path.addDot(dot3)
        path.addDot(dot4)
        path.addDot(dot5)
        then:
        def domainException = thrown(DomainException)
        domainException.message == 'Cannot add dot to path'
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
