Feature: Project

  Background: The user is logged.
    Given the user goes to login page
    And the guest fills the form with email and password

  @logout @deleteProject
  Scenario: A user creates a new Project
    When the user creates a new project with the following name "ProjectTest"
    Then the user should see the new project appear in the panel successfully
      And the user should see the name of the project on the Bar title of content panel

  @logout @deleteAllProjects
  Scenario: A user copy a project with all values option
    Given the user creates a new project with the following name "ProjectTest"
    When the user copies the project and gives it the name "New Project"
#    Then the user should see the copy success message: "Copying Folder..."
#      And the user should see the copy success message: "Folder copied!"
      And the user should see the new project appear in the panel successfully
      And the user should see the name of the list on content Task
      And the user should see the name of the project on the Bar title of content panel
