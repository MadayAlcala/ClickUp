Feature: Create new space

  @logout @deleteSpace
  Scenario: A user creates a new space
    Given the user goes to login page
    And the guest fills the form with email and password
    When the user creates a new space with the following name "New Space"
    Then the space name appear in the panel successfully
