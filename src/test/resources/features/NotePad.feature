Feature: Notepad
  Scenario: A user creates a note
    Given the guest user is logged in ClickUp web application
    When the user goes to Notepad page
    And the user creates a "AT-10" note
    And the user goes to Notepad page
    Then the user should see the note in the notes list.

  @CreateNote
  Scenario: A user converts a note to task
    Given the guest user is logged in ClickUp web application
    When the guest user goes to note page
    And the guest user converts the note to task
    And the guest user goes to list page
    Then the guest user should see the task in the list.
