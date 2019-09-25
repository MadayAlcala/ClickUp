Feature: Create new space

  @logout
  Scenario: The user create a new space
    Given The user goes to "login" page
    And The "user" fills the form with email and password
    When The user create a new space with the following name test7
    Then The space name with the name test7 appear in the panel successfully