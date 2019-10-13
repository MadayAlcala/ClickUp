Feature: Import
  @logout @deleteImport
  Scenario: Import file CSV
    Given the user goes to login page
    And the user is logged with admin credentials
    When the user imports CSV file "csvtest.csv"
    Then the import should show that it was done successfully
    And the task import should be created in a space list
