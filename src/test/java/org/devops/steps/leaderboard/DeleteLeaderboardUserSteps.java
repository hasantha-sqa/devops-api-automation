package org.devops.steps.leaderboard;

import io.cucumber.java8.En;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.devops.Context;
import org.devops.utils.Constants;

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

            this.deleteUsersReq = given().headers("Authorization", context.getAuthorizationToken(), "Authentication", context.getAuthenticationToken())
                    .param("delete-key", this.username).contentType(ContentType.JSON);

            this.deleteUserRes = this.deleteUsersReq.when().delete(Constants.BASE_URL + "/v1/users");

            System.out.println(((RequestSpecificationImpl) this.deleteUsersReq).getHeaders());
            System.out.println(this.deleteUserRes.body().prettyPrint());

        });

        Then("^user should get delete leaderboard user response with status code (\\d+)$", (Integer statusCode) -> {

            this.deleteUserValRes = this.deleteUserRes.then().statusCode(statusCode);

        });
    }
}
