package org.devops.steps.leaderboard;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import io.cucumber.java8.En;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.devops.Context;
import org.devops.utils.AllureAttachment;
import org.devops.utils.Configs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class GetLeaderBoardUsersSteps extends Context implements En {
    private RequestSpecification getUsersReq;
    private Response getUsersRes;
    private ValidatableResponse getUsersValRes;

    public GetLeaderBoardUsersSteps(Context context) {

        When("^user hits get leaderboard users endpoint$", () -> {

            this.getUsersReq = given().headers("Authentication", context.getAuthenticationToken())
                    .contentType(ContentType.JSON);

            this.getUsersRes = this.getUsersReq.when().get(Configs.BASE_URL + "/v1/users");

            AllureAttachment.addSearchDetailsToReport("GET LEADERBOARD USERS", "GET", this.getUsersReq, this.getUsersRes);

        });

        Then("^user should get leaderboard users response with status code (\\d+)$", (Integer statusCode) -> {

            this.getUsersValRes = this.getUsersRes.then().statusCode(statusCode);

        });

        And("^get leaderboard users response should return a list of users$", () -> {

            JsonArray responseArray = JsonParser.parseString(this.getUsersRes.asString()).getAsJsonArray();
            assertThat(responseArray.size(), greaterThan(0));

        });

        And("^get leaderboard users response should return users with a username and a score$", () -> {

            this.getUsersValRes.body("username", everyItem(notNullValue()));
            this.getUsersValRes.body("score", everyItem(notNullValue()));

        });
    }
}
