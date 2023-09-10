package org.devops.steps.leaderboard;

import io.cucumber.java8.En;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.devops.Context;
import org.devops.objects.LeaderboardUser;
import org.devops.utils.AllureAttachment;
import org.devops.utils.Configs;

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

            this.updateUsersReq = given().headers("Authentication", context.getAuthenticationToken())
                    .body(leaderboardUser.getRequestJson()).contentType(ContentType.JSON);

            this.updateUserRes = this.updateUsersReq.when().put(Configs.BASE_URL + "/v1/users");

            AllureAttachment.addSearchDetailsToReport("UPDATE LEADERBOARD USER", "PUT", this.updateUsersReq, this.updateUserRes);

        });

        Then("^user should get update leaderboard user response with status code (\\d+)$", (Integer statusCode) -> {

            this.updateUserValRes = this.updateUserRes.then().statusCode(statusCode);

        });
    }
}
