Feature: Task

#  @logout @deleteSpace
#  Scenario: A user creates a new task
#    Given the user goes to login page
#      And the guest fills the form with email and password
#    When the user creates a new space with the following name "TestSpace"
#      And the user creates a new list with the following name "TestList"
#      And the user creates a new task with the following name "TestTask"
#    Then the user should see the task creation success message
#      And the user should see the task listed

  @logout @deleteTask
  Scenario: A user receives notification for a task assigned to him
    Given the user logs in as admin
    When the user creates a new task with the following name "Task To Assign"
      And the user goes to page of the new task
      And the user assigns the task to a guest user
      And the user logs out
      And the user logs in as guest
      And the user goes to notifications page for admin workplace
    Then the user should see the new task listed
    When the user goes to page of the new task
    Then the user should see the message that the task was assigned to him
    When the user makes an API request for the task
    Then the user should see that he is assigned to the new task

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

#  @logout
#  Scenario: A user attaches a file to a task
#    Given the user goes to login page
#      And the guest fills the form with email and password
#      And the user creates a new task with the following name "TestTask"
#      And the user goes to page of the new task
#      And the user attaches a file from the computer to a task
#    Then the user should see the filename in the attachments section.
