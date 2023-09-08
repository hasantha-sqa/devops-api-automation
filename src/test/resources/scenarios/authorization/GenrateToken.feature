Feature: Generate Auth Token for the App

  @GetAuthToken
  Scenario: User Wants to Verify Generating an Auth Token for the App
    Given user enters key as "TEST1234567" and email as "hassey123@gmail.com"
    When user hits generate token endpoint
    Then user should get generate token response with status code 200
    And generated token should be sent to the correct email