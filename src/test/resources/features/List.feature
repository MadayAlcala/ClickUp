Feature: List

  Background: The user is logged.
    Given the user goes to login page
    And the guest fills the form with email and password

#  @logout @deleteList
#  Scenario: Create new List
#    When the user creates a new list with the following name "ListTest"
#    Then the user should see the new list appear in the panel successfully
#      And the user should see the name of the list on the Bar title of content panel
#      And the user should see the name of the list on content Task

#  @logout @deleteList
#  Scenario: Update a List
#    Given the user creates a new list with the following name "ListTest"
#    When the user updates a list with the following name "Test"
#    Then the user should see the new list appear in the panel successfully
##      And the user should see the name of the list on the Bar title of content panel
#      And the user should see the name of the list on content Task
#
#  @logout
#  Scenario: Delete new List
#    Given the user creates a new list with the following name "ListTest"
#    When the user deletes the list
##    Then the user should not see the list in the panel
#
#  @logout @deleteSpace
#  Scenario: Search Tasks in List View without any filter
#    Given the user creates a new space with the following name "SpaceTest"
#    And the user creates a new list with the following name "ListTest"
#    And the user creates the following tasks:
#      | task gatuno           |
#      | gato de navidad       |
#      | Era un task de gato   |
#      | El gato tenia un task |
#      | task                  |
#      | task2                 |
#      | task89745             |
#    When the user searches a task with "Task" keyword
#    Then the user should see displayed "7 Tasks" at the bottom of the list
#    Then the user should see the following tasks displayed
#      | task gatuno           |
#      | Era un task de gato   |
#      | El gato tenia un task |
#      | task                  |
#      | task2                 |
#      | task89745             |
#    And the user should not see the following tasks displayed
#      | gato de navidad       |
#
#  @CreateSpace
#  Scenario: A user changes a task from a status to another
#    Given the user creates a new space with the following name "SpaceTest"
#      And the user creates a new list with the following name "ListTest"
#      And the user creates a new task with the following name "TestTask"
#      And the user selects the "board" view
#    When the user drags the task to "Complete" status
#    Then the user user should see the task in "Complete" status.

  @CreateSpace
  Scenario: A user changes a list from a project to another
    Given the user creates a new space with the following name "SpaceTest"
      And the user creates a new project with the following name "First ProjectTest"
      And the user creates a new project with the following name "Second ProjectTest"
      And the user creates a new list with the following name "TestList to Move"
#    When the user moves the list to other project
#      And the user goes to other project
#    Then the user should see the list in the other project.

