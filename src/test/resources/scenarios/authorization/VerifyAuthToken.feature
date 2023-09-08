Feature: Verify Auth Token

  @VerifyAuthToken
  Scenario: User Wants to Verify an Auth Token is Still Valid
    Given user wants to verify current auth token is valid
    When user hits verify token endpoint
    Then user should get verify token response with status code 200
    And verify token response should return app id as 0 and app as "TEST1234567"
