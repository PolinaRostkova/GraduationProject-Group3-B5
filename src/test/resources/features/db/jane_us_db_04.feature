@db @ui
Feature: As a user ("advisor"), I want the clients visible in UI to match the database

  Background: this is for navigating to Docuport page
    Given user is on Docuport login page

  Scenario: Validate clients table matches client view in UI
    When user is logged in for advisor
    Then the user navigates to "Clients" page
    And the user searches names
      | Aldo Barton   |
      | Alita Effertz |
      | Antone Rippin |
    And the user should see names in the database
    Then user logs out




