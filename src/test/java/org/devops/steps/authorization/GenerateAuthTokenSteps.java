package org.devops.steps.authorization;

import org.devops.Context;
import org.devops.objects.App;
import org.devops.utils.AllureAttachment;
import org.devops.utils.Configs;
import io.cucumber.java8.En;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GenerateAuthTokenSteps extends Context implements En {
    private App app;
    private RequestSpecification generateTokenReq;
    private Response generateTokenRes;
    private ValidatableResponse generateTokenValRes;

    public GenerateAuthTokenSteps(Context context) {

        Given("^user enters key as \"([^\"]*)\" and email as \"([^\"]*)\"$", (String key, String email) -> {

            this.app = new App(key, email);

        });

        When("^user hits generate token endpoint$", () -> {

            this.generateTokenReq = given().body(this.app.getRequestJson()).contentType(ContentType.JSON);
            this.generateTokenRes = this.generateTokenReq.when().post(Configs.BASE_URL + "/auth/gentoken");

            AllureAttachment.addSearchDetailsToReport("GET TOKEN", "POST", this.generateTokenReq, this.generateTokenRes);

        });

        Then("^user should get generate token response with status code (\\d+)$", (Integer statusCode) -> {

            this.generateTokenValRes = this.generateTokenRes.then().statusCode(statusCode);

        });

        And("^generated token should be sent to the correct email$", () -> {

            this.generateTokenValRes.body("emailTo", equalTo(app.getEmail()));

        });

    }
}
