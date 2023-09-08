Feature: Update an Existing User in the Leaderboard

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

  @UpdateLeaderboardUser
  Scenario: User Wants to Verify Updating an Existing User in the Leaderboard
    Given user enters updating leaderboard user's username as "Testx" and score as 50
    When user hits update leaderboard user endpoint
    Then user should get update leaderboard user response with status code 204
