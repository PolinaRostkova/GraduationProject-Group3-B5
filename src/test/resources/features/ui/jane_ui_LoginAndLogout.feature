
@ui
Feature: As a user (advisor, supervisor, client, employee),
  I want to log in so that I can see my home page and log out

  Background: this is for navigating to Docuport page
    Given user is on Docuport login page

  Scenario Outline: Login and logout flow for each <role>
    When user logs in as "<role>"
    Then user should be able to see the home page for "<role>"
    And browser url should be "https://beta.docuport.app/"
    And user should be able to see "Received Docs" button on "Left Navigate" page
    And user should be able to see "My uploads" button on "Left Navigate" page
    When user logs out
    And user is on Docuport login page
    When user refreshes the page
    Then user is on Docuport login page

    Examples:
      | role       |
      | client     |
      | advisor    |
      | supervisor |
      | employee   |