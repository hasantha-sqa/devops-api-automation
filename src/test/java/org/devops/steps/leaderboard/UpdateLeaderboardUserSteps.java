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

public class UpdateLeaderboardUserSteps extends Context implements En {
    private LeaderboardUser leaderboardUser;
    private RequestSpecification updateUsersReq;
    private Response updateUserRes;
    private ValidatableResponse updateUserValRes;
    public UpdateLeaderboardUserSteps(Context context) {

        Given("^user enters updating leaderboard user's username as \"([^\"]*)\" and score as (\\d+)$", (String username, Integer score) -> {

            leaderboardUser = new LeaderboardUser(username, score);

        });

        When("^user hits update leaderboard user endpoint$", () -> {

            this.updateUsersReq = given().headers("Authorization", context.getAuthorizationToken(), "Authentication", context.getAuthenticationToken())
                    .body(leaderboardUser.getRequestJson()).contentType(ContentType.JSON);

            this.updateUserRes = this.updateUsersReq.when().put(Constants.BASE_URL + "/v1/users");

            System.out.println(((RequestSpecificationImpl) this.updateUsersReq).getHeaders());
            System.out.println(this.updateUserRes.body().prettyPrint());

        });

        Then("^user should get update leaderboard user response with status code (\\d+)$", (Integer statusCode) -> {

            this.updateUserValRes = this.updateUserRes.then().statusCode(statusCode);

        });
    }
}
