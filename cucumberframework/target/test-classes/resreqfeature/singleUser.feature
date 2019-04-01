Feature: get the details of user
  I want to display the details of user

  @tag1
  Scenario: get the details of users
    Given i requested the single user for 2
    When i make a request for single user
    Then single user details returned