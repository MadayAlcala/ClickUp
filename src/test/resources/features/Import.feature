Feature: Import
  @logout @deleteImport @deleteSpace @Jesus @Acceptance
  Scenario: Import file CSV
    Given the user goes to login page
    And the user is logged with admin credentials
    And the user creates a new space with the following name "Test"
    When the user imports CSV file "csvtest.csv"
    Then the import should show that it was done successfully
    And the task import should be created in a space list

  @logout @deleteImport @deleteSpace @Jesus @Acceptance
  Scenario: Import file CSV manually
    Given the user goes to login page
    And the user is logged with admin credentials
    And the user creates a new space with the following name "Test"
    When the user imports CSV file manually
      |TestTask1	This a description1	userclickup2@gmail.com	Closed	List1		High  |
      |TestTask2	This a description2	userclickup@gmail.com	Open	List1		Low   |
      |TestTask3	This a description3	admiclickup@gmail.com	Closed	List1		Urgent|
    Then the import should show that it was done successfully
    And the task import should be created in a space list

  @logout @Jesus @Negative
  Scenario: Import file CSV manually export with out data
    Given the user goes to login page
    And the user is logged with admin credentials
    When the user imports CSV file without filling in the data
    Then the web applications must show there is no data pasted to be submitted