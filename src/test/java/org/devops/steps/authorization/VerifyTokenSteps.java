package org.devops.steps.authorization;

import io.cucumber.java8.En;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.devops.Context;
import org.devops.utils.Constants;
import org.devops.utils.PropertyUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class VerifyTokenSteps extends Context implements En {

    private RequestSpecification verifyTokenReq;
    private Response verifyTokenRes;
    private ValidatableResponse verifyTokenValRes;

    public VerifyTokenSteps(Context context) {

        Given("^user wants to verify current auth token is valid$", () -> {

            context.setAuthorizationToken(PropertyUtils.readPropertyFile(Constants.AUTO_TOKEN_FILE).getProperty("Token"));

        });

        When("^user hits verify token endpoint$", () -> {

            this.verifyTokenReq = given().headers("Authorization", context.getAuthorizationToken()).contentType(ContentType.JSON);
            this.verifyTokenRes = this.verifyTokenReq.when().get(Constants.BASE_URL + "/auth/verifytoken");

            System.out.println(((RequestSpecificationImpl) this.verifyTokenReq).getHeaders());
            System.out.println(this.verifyTokenRes.body().prettyPrint());

        });

        Then("^user should get verify token response with status code (\\d+)$", (Integer statusCode) -> {

            this.verifyTokenValRes = this.verifyTokenRes.then().statusCode(statusCode);

        });

        And("^verify token response should return app id as (\\d+) and app as \"([^\"]*)\"$", (Integer appId, String app) -> {

            this.verifyTokenValRes.body("appId", equalTo(appId));
            this.verifyTokenValRes.body("app", equalTo(app));

        });

    }
}
