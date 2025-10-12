@ui
Feature: Docuport Sample Scenario

  @addNewContractor @ui
  Scenario: Practice click buttons on different pages as a client
    Given user is on Docuport login page
    When user inserts "b1g2_client@gmail.com" to "username" field on "Login" page
    And user inserts "Group2" to "password" field on "Login" page
    And user clicks "login" button on "Login" page
    And user clicks "continue" button on "Choose account" page
    Then user should be able to see the home page for client
    And user clicks "1099 Form" button on "Left navigate" page
    And user adds new contractor
    And user inserts "Luna" to "First name" field
    And user inserts "Siera" to "Last name" field
    And user inserts "123456789" to "SSN or TIN" field
    And user inserts "214 Silverleaf Avenue" to "Street address" field
    And user inserts "Apt 3B" to "Address (apt/suit)" field
    And user inserts "Boston" to "City" field
    And user selects "Massachusetts" on "State" field
    And user inserts "57919" to "Zip code" field
    And user inserts "79.00" to "Total payment" field
    And user clicks "save" button on "1099 Form" page
    And user should see the contractor displayed in the list
