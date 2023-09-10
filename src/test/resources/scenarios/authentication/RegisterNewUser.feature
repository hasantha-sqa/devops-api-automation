Feature: Register New User

  Background: Verify the Auth Token is Still Valid
    Given user wants to verify current auth token is valid
    When user hits verify token endpoint
    Then user should get verify token response with status code 200
    And verify token response should return app id as 0 and app as "TEST1234567"

  @RegisterUser @RegisterNewUser
  Scenario: User Wants to Verify Registering as a New User
    Given user enters new user's username as "Test321" and password as "Test123"
    When user hits register new user endpoint
    Then user should get register new user response with status code 200

  @RegisterUser @RegisterExistingUser
  Scenario: User Wants to Verify Registering as an Existing User
    Given user enters new user's username as "Test321" and password as "Test123"
    When user hits register new user endpoint
    Then user should get register new user response with status code 400
    And register new user response should return an error as "user already exist"