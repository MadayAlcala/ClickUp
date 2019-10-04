Feature: List

  Background: The user is logged.
    Given the user goes to login page
    And the guest fills the form with email and password

#  @logout @deleteList
#  Scenario: Create new List
#    When the user creates a new list with the following name "ListTest"
#    Then the user should see the new list appear in the panel successfully
#
#  @logout @deleteList
#  Scenario: Update a List
#    Given the user creates a new list with the following name "ListTest"
#    When the user updates a list with the following name "Test"
#    Then the user should see the new list appear in the panel successfully
#
#  @logout
#  Scenario: Delete new List
#    Given the user creates a new list with the following name "ListTest"
#    When the user deletes the list
#    Then the user should not see the list in the panel

  @logout @deleteSpace
  Scenario: Filter and Search Tasks in List View without any filter
    Given the user creates a new space with the following name "SpaceTest"
    And the user creates a new list with the following name "ListTest"
    Given the user creates the following tasks:
      | task gatuno           |
      | gato de navidad       |
      | Era un task de gato   |
      | El gato tenia un task |
      | task                  |
      | task2                 |
      | task89745             |
    And the user searches a task with "Task" keyword
#    Then the user should see the following tasks displayed
#      | task |
#      | task |
#    And the user should not see the following tasks displayed
#      | task |
#      | task |
#    Examples:
#      | newTask |
#      | task    |

#  @CreateSpace
#  Scenario: Drag and drop reordering in board view
#    When the guest user creates a workplace
#    And the guest user creates a space
#    And the guest user creates a list
#    And the guest user creates a task
#    And the guest user selects the “board” view
#    And the guest user drags the task to “Done” status
#    Then the guest user should see the task in “Done” status.
