@db
Feature: Verify the login user exists, is active,
  and has a valid role (Client, Advisor, Supervisor, Employee) in Data Base

  Scenario: Verify that all expected user roles are active in the DB
    When I query the database for expected test users
    Then I validate that all users exist, are active, and have valid roles


