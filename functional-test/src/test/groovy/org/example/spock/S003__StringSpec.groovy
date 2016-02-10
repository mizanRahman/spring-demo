package org.example.spock

import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by mac on 2/10/16.
 */
class S003__StringSpec extends Specification {

    def "#data length should be #length"() {

        expect: "#data length should be #length"
        data.length() == length

        where:
        data    | length
        "hello" | 5
        "ok"    | 2

    }
}
