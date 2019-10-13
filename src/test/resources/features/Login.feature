Feature: Login

  @logout @Maday
  Scenario: Login as registered user
    Given the user goes to login page
    When the guest fills the form with email and password
    Then Username should appear in the panel
