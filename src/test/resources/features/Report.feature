Feature: Report

  @Acceptance
  Scenario: Save report
    Given the user goes to login page
    And the user is logged with admin credentials
    And the user goes to the page
    And the guest user select file destination
    Then the file was downloaded successfully
#
#  @Acceptance
#  Scenario: Verify that completed tasks are displayed on the reports page
#    Given the guest user is logged in ClickUp web application
#    When the guest user creates a workplace
#    And the guest user creates a space
#    And the guest user creates a list
#    And the guest user creates a task
#    And the guest user moves the task to “Done” status
#    When the guest user goes to report page
#    Then the task should be displayed as Completed.
