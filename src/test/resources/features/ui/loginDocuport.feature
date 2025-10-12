@ui
Feature: Docuport login and logout feature

  Background: this is for navigating to Docuport page
    Given user is on Docuport login page

  @ui
  Scenario Outline: Login for all the <role>
    #Given user is on Docuport login page
    When user enters username for "<roleUsername>"
    And user enters password for needed role
    And user clicks login button
    Then user should be able to see the home page for "<role>"

    Examples:
      | roleUsername              | role       |
      | b1g2_client@gmail.com     | client     |
      | b1g2_advisor@gmail.com    | advisor    |
      | b1g2_supervisor@gmail.com | supervisor |
      | b1g2_employee@gmail.com   | employee   |