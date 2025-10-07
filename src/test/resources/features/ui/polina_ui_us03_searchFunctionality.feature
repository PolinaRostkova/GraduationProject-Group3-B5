@ui
Feature: Received Docs filters with two matching results

  @receivedPageSearch @ui
  Scenario: Tag + Upload date + Uploaded by yields no results
    Given user is on Docuport login page
  When user inserts "b1g2_client@gmail.com" to "username" field on "Login" page
  And user inserts "Group2" to "password" field on "Login" page
  And user clicks "login" button on "Login" page
  And user clicks "continue" button on "Choose account" page
  And user clicks "Received Doc" button on "Left Navigate" page
  And user clicks "Search" button on "Received doc" page
  And user inserts "GMT20250920-133135_Recording.transcript.vtt" to "Document name" field on "Received doc" page
  And user clicks "Tags" button on "Received doc" page
  #And user clicks "Other documents" option on "Tags" dropdown
  And user clicks "IRS State Letter" option on "Tags" dropdown
  And user clicks "Service" button on "Received doc" page
  And user clicks "Bookkeeping" option on "Service" dropdown
  And user clicks "Upload date" button on "Received doc" page
  And user clicks "Previous month" option on "Upload date" dropdown
  And user validates "September 2025" is displayed on "Upload date" dropdown
  And user clicks "9-25-25" option on "Upload date" dropdown
  And user clicks "Uploaded by" button on "Received Doc" page
  And user clicks "Batch1 Group1" option on "Uploaded by" dropdown
  And user clicks "Search submit" button on "Received doc" page
  Then user should see message "Your search returned no results. Make sure you search properly" on "Received Doc" page