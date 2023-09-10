Feature: User Login

  Background: Verify the Auth Token is Still Valid
    Given user wants to verify current auth token is valid
    When user hits verify token endpoint
    Then user should get verify token response with status code 200
    And verify token response should return app id as 0 and app as "TEST1234567"

  @UserLogin @UserLoginCorrectCredentials
  Scenario: User Wants to Verify User Login with Correct Credentials
    Given user enters login username as "Test321" and password as "Test123"
    When user hits login endpoint
    Then user should get login response with status code 200
    And login response should return an authentication token
    And login response should return expire time as "3 min"

  @UserLogin @UserLoginInCorrectCredentials
  Scenario Outline: User Wants to Verify User Login with <scenario>
    Given user enters login username as "<username>" and password as "<password>"
    When user hits login endpoint
    Then user should get login response with status code 401
    And user login response should return an error as "incorrect user name and password"
    Examples:
      | scenario                                | username | password |
      | Correct User Name, Incorrect Password   | Test321  | Test1    |
      | Incorrect User Name, Correct Password   | Test3    | Test123  |
      | Incorrect User Name, Incorrect Password | Test3    | Test1    |