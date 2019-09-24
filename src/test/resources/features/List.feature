Feature: List

  Scenario: Create new List
    Given The user goes to "login" page
    When The "user" fills the form with email and password
    When The user create a new list with the following name Hello12
    Then The list name with the name Hello12 appear in the panel successfully