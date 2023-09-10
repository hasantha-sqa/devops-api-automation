Feature: Get the User Information by User Name

  Background: Verify the Auth Token is Still Valid and Login
    Given user wants to verify current auth token is valid
    When user hits verify token endpoint
    Then user should get verify token response with status code 200
    And verify token response should return app id as 0 and app as "TEST1234567"

    Given user enters login username as "Test321" and password as "Test123"
    When user hits login endpoint
    Then user should get login response with status code 200
    And login response should return an authentication token
    And login response should return expire time as "3 min"

  @GetUserByName
  Scenario Outline: User Wants to Verify Returning User Information by User Name
    Given user enters user name as "<userName>"
    When user hits get user by name endpoint
    Then user should get user by name response with status code 200
    And get user by name response should return only one user
    And get user by name response should return username as "<userName>"
    Examples:
      | userName |
      | Test321  |
      | Test456  |
      | Test789  |