package org.example.spock

import groovy.json.JsonBuilder
import groovy.util.logging.Slf4j
import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import spock.lang.Ignore
import spock.lang.Specification

/**
 * Created by mac on 1/31/16.
 */
@Slf4j
class S001__GithubServiceSpec extends Specification {

    def myService = new RESTClient("http://localhost:8080")
    def json = new JsonBuilder()

    @Ignore
    def "view all cards"() {
        when: "I want to view all cards"
        def response = myService.get(
                path: '/cards'
        )

        then: "I get all posts"
        log.info("hello world")
        response.status == 200
    }

    @Ignore
    def "create a new post"() {

        given: "i am a registered user"

        when: "i make a post request to /posts"
        def response = myService.post(
                path: '/cards',
                requestContentType: ContentType.JSON,
                body: json() {
                    pan '12313'
                    balance 12
                }
        )

        then: "a new resource is created"
        response.status == 200
    }
}
