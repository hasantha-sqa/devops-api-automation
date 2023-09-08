package org.devops.steps.leaderboard;

import io.cucumber.java8.En;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.devops.Context;
import org.devops.objects.LeaderboardUser;
import org.devops.utils.Constants;

import static io.restassured.RestAssured.given;

public class AddLeaderboardUserSteps extends Context implements En {
    private LeaderboardUser leaderboardUser;
    private RequestSpecification addUsersReq;
    private Response addUsersRes;
    private ValidatableResponse addUserValRes;

    public AddLeaderboardUserSteps(Context context) {

        Given("^user enters new leaderboard user's username as \"([^\"]*)\" and score as (\\d+)$", (String username, Integer score) -> {

            leaderboardUser = new LeaderboardUser(username, score);

        });

        When("^user hits add leaderboard user endpoint$", () -> {

            this.addUsersReq = given().headers("Authorization", context.getAuthorizationToken(), "Authentication", context.getAuthenticationToken())
                    .body(leaderboardUser.getRequestJson()).contentType(ContentType.JSON);

            this.addUsersRes = this.addUsersReq.when().post(Constants.BASE_URL + "/v1/users");

            System.out.println(((RequestSpecificationImpl) this.addUsersReq).getHeaders());
            System.out.println(this.addUsersRes.body().prettyPrint());

        });

        Then("^user should get add leaderboard user response with status code (\\d+)$", (Integer statusCode) -> {

            this.addUserValRes = this.addUsersRes.then().statusCode(statusCode);

        });
    }
}
