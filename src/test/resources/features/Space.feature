Feature: Create new space
  Scenario: The user create a new space
    Given The user goes to "login" page
     And The user fills the form with "email" and "password"
    When The user create a new space with the following name test1
    Then The space name with the name test1 appear in the panel successfully