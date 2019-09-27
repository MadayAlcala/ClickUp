Feature: Create new space

  @logout
  Scenario: The user create a new space
    Given The user goes to login page
      And The user fills the form with email and password
    When The user creates a new space with the following name "Test"
    Then The space name with the name "Test" appear in the panel successfully
