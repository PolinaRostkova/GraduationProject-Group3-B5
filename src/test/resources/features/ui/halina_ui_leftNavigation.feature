Feature: Docuport left module navigation for all roles

  Background:
    Given user is on Docuport login page

  @left_navigation
  Scenario Outline: Validate modules and headers for each role
    When user logs in as "<role>"
    Then user validates "<role>" from menu for role
    And user clicks and validates all modules for "<role>"


    Examples:
      | role       |
      | advisor    |
      | supervisor |
      | employee   |
      | client     |


