package org.devops.steps.authentication;

import io.cucumber.java8.En;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.devops.Context;
import org.devops.objects.UserCredentials;
import org.devops.utils.AllureAttachment;
import org.devops.utils.Configs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class UserLoginSteps extends Context implements En {
    private UserCredentials userCredentials;
    private RequestSpecification userLoginReq;
    private Response userLoginRes;
    private ValidatableResponse userLoginValRes;

    public UserLoginSteps(Context context) {

        Given("^user enters login username as \"([^\"]*)\" and password as \"([^\"]*)\"$", (String username, String password) -> {

            this.userCredentials = new UserCredentials(username, password);

        });

        When("^user hits login endpoint$", () -> {

            this.userLoginReq = given().headers("Authorization", context.getAuthorizationToken())
                    .body(this.userCredentials.getRequestJson()).contentType(ContentType.JSON);

            this.userLoginRes = this.userLoginReq.when().post(Configs.BASE_URL + "/auth/user/login");

            AllureAttachment.addSearchDetailsToReport("USER LOGIN", "POST", this.userLoginReq, this.userLoginRes);

        });

        Then("^user should get login response with status code (\\d+)$", (Integer statusCode) -> {

            this.userLoginValRes = this.userLoginRes.then().statusCode(statusCode);
            context.setAuthenticationToken(this.userLoginValRes.extract().path("token"));

        });

        And("^login response should return an authentication token$", () -> {

            this.userLoginValRes.body("token", notNullValue());

        });

        And("^login response should return expire time as \"([^\"]*)\"$", (String expiryTime) -> {

            this.userLoginValRes.body("expiresIn", equalTo(expiryTime));

        });

        And("^user login response should return an error as \"([^\"]*)\"$", (String error) -> {

            this.userLoginValRes.body("error", equalTo(error));

        });
    }
}
