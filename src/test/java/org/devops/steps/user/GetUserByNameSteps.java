package org.devops.steps.user;

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
import org.hamcrest.CoreMatchers;

import java.io.InputStream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class GetUserByNameSteps extends Context implements En {
    private String username;
    private RequestSpecification getUserReq;
    private Response getUserRes;
    private ValidatableResponse getUserValRes;

    public GetUserByNameSteps(Context context) {

        Given("^user enters user name as \"([^\"]*)\"$", (String username) -> {

            this.username = username;

        });

        When("^user hits get user by name endpoint$", () -> {

            this.getUserReq = given().headers("Authentication", context.getAuthenticationToken()).pathParam("{name}", this.username)
                    .contentType(ContentType.JSON);

            this.getUserRes = this.getUserReq.when().get(Configs.BASE_URL + "/v1/user/{name}");

            AllureAttachment.addSearchDetailsToReport("GET USER BY NAME", "GET", this.getUserReq, this.getUserRes);

        });

        Then("^user should get user by name response with status code (\\d+)$", (Integer statusCode) -> {

            this.getUserValRes = this.getUserRes.then().statusCode(statusCode);

        });

        And("^get user by name response should return only one user$", () -> {

            JsonArray responseArray = JsonParser.parseString(this.getUserRes.asString()).getAsJsonArray();
            assertThat(responseArray.size(), equalTo(1));

        });

        And("^get user by name response should return username as \"([^\"]*)\"$", (String username) -> {

            this.getUserValRes.body("username", equalTo(username));

        });
    }
}
