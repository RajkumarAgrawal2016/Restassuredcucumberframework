Feature: get the list of resourse users
  I want to display the list of resourse users

  @tag1
  Scenario: get the list of resourse users
    Given i requested the user
    When i make a request
    Then list users returned
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
      |pantone_value  |
    And list of user verified