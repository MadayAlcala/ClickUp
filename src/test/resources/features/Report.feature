Feature: Report

  @logout @deleteImport @deleteSpace @Jesus @Acceptance
  Scenario: Verify that completed tasks are displayed on the reports page
    Given the user goes to login page
    And the user is logged with admin credentials
    And the user creates a new space with the following name "Test"
    And the user imports CSV file "csvtest.csv"
    When the user goes to reporting page
    Then the task should be displayed as Completed.

