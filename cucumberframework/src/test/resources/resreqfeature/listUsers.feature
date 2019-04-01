Feature: get the list of users
  I want to display the list of users

  @tag1
  Scenario: get the list of users
    Given i requested the user
    When i make a request
    Then list users returned
    And all the fields expected for the resqres are listed
    And valid structure of the data return
      | page          |
      | per_page      |
      | total         |
      | total_page    |
      | data          |
      | id            |
      | name          |
      | year          |
      | color         |
      | pantone_value |
