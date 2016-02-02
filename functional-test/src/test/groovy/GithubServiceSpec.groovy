import groovy.json.JsonBuilder
import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import spock.lang.Specification

/**
 * Created by mac on 1/31/16.
 */

class GithubServiceSpec extends Specification {

    def myService = new RESTClient("http://localhost:8080")
    def json = new JsonBuilder()

    def "view all cards"() {

        given: "I have some cards"
        when: "I want to view all cards"
        def response = myService.get(
                path: '/cards'
        )

        then: "I get all posts"
        response.status == 200
    }

    def "create a new post"() {

        given: "i am a registered user"

        when: "i make a post request to /posts"
        def response = myService.post(
                path: '/cards',
                requestContentType: ContentType.JSON,
                body: json() {
                    pan '12312'
                    balance 12
                }
        )

        then: "a new resource is created"
        println response.data
        response.status == 200
    }
}
