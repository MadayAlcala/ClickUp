Feature: Project

  Background: The user is logged.
    Given the user logs in as member

  @logout @deleteProject @Maday
  Scenario: A user creates a new Project
    When the user creates a new project with the following name "Project"
    Then the user should see the new project appear in the panel successfully
      And the user should see the name of the project on the Bar title of content panel

  @logout @deleteAllProjects @Maday
  Scenario: A user copy a project with all values option
    Given the user creates a new project with the following name "Project"
    When the user copies the project and gives it the name "Project copied"
    Then the user should see the copy success message: "Copying Folder..."
      And the user should see the copy success message: "Folder copied!"
      And the user should see the new project appear in the panel successfully
      And the user should see the name of the list on content Task
      And the user should see the name of the project on the Bar title of content panel
