Feature: List

  Background: The user is logged.
    Given the user logs in as member

  @logout @deleteList @Maday
  Scenario: A user creates a new List
    When the user creates a new list with the following name "List"
    Then the user should see the new list appear in the panel successfully
      And the user should see the name of the list on the Bar title of content panel
      And the user should see the name of the list on content Task

  @logout @deleteList @Maday
  Scenario: A user updates a List
    Given the user creates a new list with the following name "List"
    When the user updates a list with the following name "List Updated"
    Then the user should see the new list appear in the panel successfully
      And the user should see the name of the list on content Task

  @logout @deleteList @Maday
  Scenario: A user searches Tasks in List View without any filter
    Given the user creates a new list with the following name "List Search"
      And the user creates the following tasks:
        | task test              |
        | A task with attachment |
        | The admin has a task   |
        | task                   |
        | task2                  |
        | task89745              |
        | without keyword        |
    When the user searches a task with "Task" keyword
    Then the user should see displayed "6 Tasks" at the bottom of the list

  @logout @deleteAllLists @Maday
  Scenario: Copy a list with all values and folderless list options
    Given the user creates a new list with the following name "List"
    When the user copies the list with FOLDERLESS LIST option and gives it the name "List Copied"
    Then the user should see the copy success message: "Copying List..."
      And the user should see the copy success message: "List copied!"
      And the user should see the new list appear in the panel successfully
      And the user should see the name of the list on the Bar title of content panel
      And the user should see the name of the list on content Task
