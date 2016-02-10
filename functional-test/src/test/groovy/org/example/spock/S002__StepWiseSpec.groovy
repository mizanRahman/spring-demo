package org.example.spock

import groovy.util.logging.Slf4j
import spock.lang.Specification
import spock.lang.Stepwise

/**
 * Created by mac on 2/10/16.
 */
@Stepwise
@Slf4j
class S002__StepWiseSpec extends Specification {
    def "step 1"() {
        log.info("step 1")
        expect: true
    }

    def "step 2"() {
        log.info("step 2")
        expect: true
    }

    def "step 3"() {
        log.info("step 3")
        expect: true
    }
}
