Feature: Login

  @logout
  Scenario: Login as registered user
    Given The user goes to login page
    When The guest fills the form with email and password
    Then Username should appear in the panel
