
Feature: Docuport login and logout feature

  Background: this is for navigating to Docuport page
    Given user is on Docuport login page

  Scenario Outline: Login for all the <role>
    #Given user is on Docuport login page
    When user is logged in for <roleUsername>
    Then user should be able to see the home page for <role>

    Examples:
      | roleUsername | role       |
      | client       | client     |
      | advisor      | advisor    |
      | supervisor   | supervisor |
      | employee     | employee   |