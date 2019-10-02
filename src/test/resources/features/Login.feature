Feature: Login

  @logout
  Scenario: Login as registered user
    Given the user goes to login page
    When the user fills the form with email and password
    Then Username should appear in the panel
