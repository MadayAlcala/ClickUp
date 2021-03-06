Feature: Task

  @logout @deleteSpace @Alejandro
  Scenario: A user creates a new task
    Given the user logs in as guest
    When the user creates a new space with the following name "New Space"
      And the user creates a new list with the following name "New List"
      And the user creates a new task with the following name "New Task"
    Then the user should see the task creation success message
      And the user should see the new task listed
    When the user goes to page of the new task
      And the user fills in the following fields
        | Description | Task Description |
        | Priority    | Urgent           |
        | Start Date  | Today            |
        | Due Date    | Tomorrow         |
      And the user goes to page of the new task
    Then the user should see the provided information

  @logout @deleteTask @Alejandro
  Scenario: A user receives notification for a task assigned to him
    Given the user logs in as admin
    When the user creates a new task with the following name "Task To Assign"
    And the user goes to page of the new task
    And the user assigns the task to a guest user
    And the user logs out
    And the user logs in as guest
    And the user goes to notifications page for admin workplace
    Then the user should see the new task listed in notifications
    When the user goes to page of the new task
    Then the user should see the message that the task was assigned to him
    When the user makes an API request for the task
    Then the user should see that he is assigned to the new task

  @logout @deleteSpace @Alejandro
  Scenario: A user changes a task from one list to another
    Given the user logs in as guest
      And the user is at an existing space
    When the user creates a first list with the following name "First List"
      And the user creates a second list with the following name "Second List"
      And the user creates a new task with the following name "Task to Move"
      And the user moves the task to the first list
    Then the user should see the task movement success message
      And the user should not see the task listed
    When the user goes to the first list
    Then the user should see the new task listed
    When the user moves the task to the second list
    Then the user should not see the task listed
    When the user goes to the second list
    Then the user should see the new task listed

  @logout @deleteTask @Alejandro
  Scenario: A user attaches a file to a task
    Given the user logs in as guest
      And the user creates a new task with the following name "Task without Attachment"
      When the user goes to page of the new task
      And the user attaches the file "api.png" from the computer to the new task
    Then the user should see the file in the attachments section

  @logout @deleteList @Maday
  Scenario: A user drags a task from a status and drop it to another
    Given the user logs in as member
      And the user creates a new list with the following name "List"
      And the user creates a new task with the following name "Task"
    When the user drags the task to Complete status
    Then the user user should see the task in complete status
