package org.devops.steps.leaderboard;

import io.cucumber.java8.En;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.devops.Context;
import org.devops.utils.AllureAttachment;
import org.devops.utils.Configs;

import static io.restassured.RestAssured.given;

public class DeleteLeaderboardUserSteps extends Context implements En {
    private String username;
    private RequestSpecification deleteUsersReq;
    private Response deleteUserRes;
    private ValidatableResponse deleteUserValRes;

    public DeleteLeaderboardUserSteps(Context context) {

        Given("^user enters deleting leaderboard user's username as \"([^\"]*)\"$", (String username) -> {

            this.username = username;

        });

        When("^user hits delete leaderboard user endpoint$", () -> {

            this.deleteUsersReq = given().headers("Authentication", context.getAuthenticationToken(), "delete-key", this.username)
                    .contentType(ContentType.JSON);

            this.deleteUserRes = this.deleteUsersReq.when().delete(Configs.BASE_URL + "/v1/users");

            AllureAttachment.addSearchDetailsToReport("DELETE LEADERBOARD USER", "DELETE", this.deleteUsersReq, this.deleteUserRes);

        });

        Then("^user should get delete leaderboard user response with status code (\\d+)$", (Integer statusCode) -> {

            this.deleteUserValRes = this.deleteUserRes.then().statusCode(statusCode);

        });
    }
}
