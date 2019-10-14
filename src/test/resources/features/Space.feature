Feature: Space
  @logout @deleteSpace
  Scenario: A user creates a new space
    Given the user goes to login page
    And the guest fills the form with email and password
    When the user creates a new space with the following name "New Space"
    Then the space name appear in the panel successfully

#  Scenario: The user create a custom space
#    Given the user goes to login page
#    And the user is logged with admin credentials
#    When the user creates a new space
#      | Name      | Test Space                                                                                            |
#      | Who       | Admin Workplace                                                                                       |
#      | Type      | Scrum                                                                                                 |
#      | ClickApps | multiple, time traking, priority, tagss, Time Estimates, Custom fields, Dependency Warning, Due Dates |
#    Then the information should be the same on "Review Page"
#    And the information should be the same on "Settings "
