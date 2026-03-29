package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class PostSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("https://jsonplaceholder.typicode.com")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")

  val scn = scenario("Load test posts API")
    .exec(
      http("GET post")
        .get("/posts/1")
        .check(status.is(200))
    )
    .pause(1)
    .exec(
      http("CREATE post")
        .post("/posts")
        .body(StringBody(
          """
          {
            \"userId\": 1,
            \"title\": \"load test\",
            \"body\": \"gatling\"
          }
          """
        )).asJson
        .check(status.in(200, 201))
    )

  setUp(
    scn.inject(
      rampUsers(50).during(10.seconds),
      constantUsersPerSec(10).during(20.seconds)
    )
  ).protocols(httpProtocol)
}