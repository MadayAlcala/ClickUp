Feature: List

  Background: The user is logged.
    Given the user goes to login page
    And the guest fills the form with email and password

  @logout @deleteList
  Scenario: A user creates a new List
    When the user creates a new list with the following name "ListTest"
    Then the user should see the new list appear in the panel successfully
    And the user should see the name of the list on the Bar title of content panel
    And the user should see the name of the list on content Task

  @logout @deleteList
  Scenario: A user updates a List
    Given the user creates a new list with the following name "ListTest"
    When the user updates a list with the following name "Test"
    Then the user should see the new list appear in the panel successfully
    And the user should see the name of the list on content Task

  @logout @deleteProject
  Scenario: A user creates a new Project
    When the user creates a new project with the following name "ProjectTest"
    Then the user should see the new project appear in the panel successfully
    And the user should see the name of the project on the Bar title of content panel
    And the user should see the name of the list on content Task

  @logout @deleteList
  Scenario: A user searches Tasks in List View without any filter
    Given the user creates a new list with the following name "ListTest"
    And the user creates the following tasks:
      | task test              |
      | new car                |
      | A task with attachment |
      | The admin has a task   |
      | task                   |
      | task2                  |
      | task89745              |
    When the user searches a task with "Task" keyword
    Then the user should see displayed "7 Tasks" at the bottom of the list

  @logout @deleteList
  Scenario: A user drags a task from a status and drop it to another
    Given the user creates a new list with the following name "ListTest"
    And the user creates a new task with the following name "TestTask"
    And the user selects the board view
    When the user drags the task to Complete status
    Then the user user should see the task in complete status.
