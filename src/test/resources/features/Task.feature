Feature: Task
  Scenario: A user receives notification for a task assigned to him
    Given the admin user is logged in ClickUp web application
    When the admin user goes to workplace page
      And the admin user creates a space                # podria ir como Hook?
      And the admin user creates a list                 # podria ir como Hook?
      And the admin user creates a task
      And the admin user assigns the task to guest user
      And the admin user logs out
    When the guest user is logged in ClickUp web application
      And the guest user goes to workplace page
    Then the guest user should see the task listed in the notification result.

  @DeleteList
  Scenario: A user changes a task from one list to another
    Given The user goes to "login" page
    And The "user" fills the form with email and password
    When The user goes to "workplace" page
      And The user goes to "space" page
      And the user creates lists
      And the user creates a second list
      And the user creates a task in the second list
      And the user moves the task to the first list
      And The user goes to the first list page
    Then the user should see the task in the first list
    When the user moves  the task to the second list
    Then the user should see the task in the second list

  Scenario: A user attaches a file to a task
    Given the guest user is logged in ClickUp web application
    When the guest user goes to workplace page        # podria ir como Hook?
      And the admin user creates a space                # podria ir como Hook?
      And the guest user creates a list
      And the guest user creates a task
      And the guest user goes to task page
      And the guest user attaches a file from the computer to a task
    Then the guest user should see the filename in the attachments section.

  @AttachFile
  Scenario: A user downloads an attachment from a task
    Given the guest user is logged in ClickUp web application
      And the guest user goes to task with attachment page
      And the guest downloads the task file to the hard drive
    Then the guest user should see the file in the hard drive.

  @CreateTask
  Scenario: A user minimizes a task to the bottom right corner
    Given the guest user is logged in ClickUp web application
      And the guest user goes to task page
      And the guest user minimizes the task window
    Then the guest user should see the task anchored in the bottom right corner
    When the guest user goes to another list page
     Then the guest user should see the task anchored in the bottom right corner
