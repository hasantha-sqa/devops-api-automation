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
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class GetLeaderBoardUsersSteps extends Context implements En {
    private RequestSpecification getUsersReq;
    private Response getUsersRes;
    private ValidatableResponse getUsersValRes;

    public GetLeaderBoardUsersSteps(Context context) {

        When("^user hits get leaderboard users endpoint$", () -> {

            this.getUsersReq = given().headers("Authorization", context.getAuthorizationToken(), "Authentication", context.getAuthenticationToken())
                    .contentType(ContentType.JSON);

            this.getUsersRes = this.getUsersReq.when().get(Constants.BASE_URL + "/v1/users");

            System.out.println(((RequestSpecificationImpl) this.getUsersReq).getHeaders());
            System.out.println(this.getUsersRes.body().prettyPrint());

        });

        Then("^user should get leaderboard users response with status code (\\d+)$", (Integer statusCode) -> {

            this.getUsersValRes = this.getUsersRes.then().statusCode(statusCode);

        });

        And("^get leaderboard users response should return a list of users$", () -> {
//            TODO
            this.getUsersValRes.body("", hasSize(greaterThan(0)));
        });

        And("^get leaderboard users response should return users with a username and a score$", () -> {

            this.getUsersValRes.body("username", everyItem(notNullValue()));
            this.getUsersValRes.body("score", everyItem(notNullValue()));

        });
    }
}
