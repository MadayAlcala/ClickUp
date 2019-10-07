Feature: Import
  @logoutImport
  Scenario: Import file CSV
    Given the user goes to login page
    And the user is logged with admin credentials
    When the user import CSV file
    Then the import is successfully
