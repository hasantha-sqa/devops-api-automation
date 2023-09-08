Feature: Get the List of Users in the Leaderboard

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

  @GetLeaderboardUsers
  Scenario: User Wants to Verify the Users in the Leaderboard
    When user hits get leaderboard users endpoint
    Then user should get leaderboard users response with status code 200
    And get leaderboard users response should return a list of users
    And get leaderboard users response should return users with a username and a score
