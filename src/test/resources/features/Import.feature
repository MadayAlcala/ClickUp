Feature: Import
  Scenario: Import some information to web page
    Given The user goes to login page
      And The user fills the form with email and password
      And The user creates a new space with the following name "Test1"
    When The user access to import and fill fields