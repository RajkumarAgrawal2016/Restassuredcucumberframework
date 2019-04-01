Feature: get the details of user not found
  I want to display the details of user

  @tag1
  Scenario: get the details of users not found
    Given i requested of user for 23
    When i make a request for not found user
    Then not found details returned