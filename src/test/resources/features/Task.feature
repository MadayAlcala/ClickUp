Feature: Task
#
#  @logout @deleteSpace
#  Scenario: A user creates a new task
#    Given the user goes to login page
#      And the guest fills the form with email and password
#    When the user creates a new space with the following name "TestSpace"
#      And the user creates a new list with the following name "TestList"
#      And the user creates a new task with the following name "TestTask"
#    Then the user should see the task creation success message
#      And the user should see the task listed
#
#  @logout @deleteTask
#  Scenario: A user receives notification for a task assigned to him
#    Given the user goes to login page
#      And the admin fills the form with email and password
#    When the user creates a new task with the following name "Task To Assign"
#    Then the task should not have any assignees
#    When the user goes to page of the new task
#      And the admin user assigns the task to a guest user
#      And the admin user logs out
#    When the user logs as guest
#      And the user goes to notifications page for admin workplace
#    Then the user should see the task listed
#      And the user should be the asignee
#
#  @logout @deleteTask
#    Scenario: A user changes a task from one list to another
#      Given the user goes to login page
#        And the guest fills the form with email and password
#      When the user creates a first list with the following name "First TestList"
#        And the user creates a second list with the following name "Second TestList"
#        And the user creates a new task with the following name "Test to Move"
#        And the user moves the task to the first list
#      Then the user should see the task movement success message
#        And the user should not see the task listed
#      When the user goes to the first list
#      Then the user should see the task listed
#      When the user moves the task to the second list
#      Then the user should not see the task listed
#      When the user goes to the second list
#      Then the user should see the task listed
#
#  @logout @deleteList
#  Scenario: A user drags a task from a status and drop it to another
#    Given the user goes to login page
#    And the guest fills the form with email and password
#    And the user creates a new list with the following name "Prueba"
#    And the user creates a new task with the following name "TestTask"
#    And the user selects the board view
#    When the user drags the task to Complete status
#    Then the user user should see the task in complete status.
