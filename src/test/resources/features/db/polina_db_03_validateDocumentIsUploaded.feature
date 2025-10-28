@ui @db
Feature: After uploading the document validate it is stored in Data Base

  Scenario: User uploads a document and it is persisted
    Given user is logged in for advisor
    And user clicks "My uploads" button on "Left Navigate" page
    And user clicks "Upload Documents" button on "My uploads" page
    #And user clicks "Upload file" button on "My uploads" page
    And user uploads a document
    And user clicks "Client name" button on "My uploads" page
    And user clicks "Alita Effertz" option on "Client name" dropdown
    And user clicks "Service name" button on "My uploads" page
    And user clicks "Consultancy" option on "Service name" dropdown
    And user clicks "IRS/State Letter" button on "My uploads" page
    And user clicks "Q1" button on "My uploads" page
    And user clicks "Upload" button on "My uploads" page
    And user validates document is uploaded with the name "PolinaTestDocument.txt"
    Then the document record with name "PolinaTestDocument.txt" should exist in DB with:
      | is_deleted   | false                  |
      | display_name | PolinaTestDocument.txt |

    Then I delete document "PolinaTestDocument.txt" from DB
