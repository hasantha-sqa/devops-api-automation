package org.devops.steps.authentication;

import io.cucumber.java8.En;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.devops.Context;
import org.devops.objects.UserCredentials;
import org.devops.utils.Constants;

import static io.restassured.RestAssured.given;

public class RegisterNewUserSteps extends Context implements En {
    private UserCredentials userCredentials;
    private RequestSpecification registerNewUserReq;
    private Response registerNewUserRes;
    private ValidatableResponse registerNewUserValRes;

    public RegisterNewUserSteps(Context context) {


        Given("^user enters new user's username as \"([^\"]*)\" and password as \"([^\"]*)\"$", (String username, String password) -> {

            this.userCredentials = new UserCredentials(username, password);

        });

        When("^user hits register new user endpoint$", () -> {

            this.registerNewUserReq = given().headers("Authorization", context.getAuthorizationToken())
                    .body(this.userCredentials.getRequestJson()).contentType(ContentType.JSON);

            this.registerNewUserRes = this.registerNewUserReq.when().post(Constants.BASE_URL + "/auth/user/register");

            System.out.println(((RequestSpecificationImpl) this.registerNewUserReq).getBody());
            System.out.println(this.registerNewUserRes.body().prettyPrint());

        });

        Then("^user should get register new user response with status code (\\d+)$", (Integer statusCode) -> {

            this.registerNewUserValRes = this.registerNewUserRes.then().statusCode(statusCode);

        });
    }
}
