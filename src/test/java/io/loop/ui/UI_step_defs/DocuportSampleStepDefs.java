package io.loop.ui.UI_step_defs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.ui.pages.POM;
import io.loop.utilities.BrowserUtils;
import io.loop.utilities.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class DocuportSampleStepDefs {

    private static final Logger LOGGER = LogManager.getLogger(DocuportSampleStepDefs.class);
    POM pages = new POM();

    @When("user inserts {string} to {string} field on {string} page")
    public void user_inserts_to_field_on_page(String input, String field, String page) {

        switch (page.toLowerCase().trim()) {
            case "login" -> {
                    pages.getLoginPage().insertField(field, input);
                    LOGGER.info(input + " was sent to the " + field);
            }
            case "received doc" -> {
                pages.getReceivedDocsPage().insertField(field, input);
                LOGGER.info(input + " was sent to the " + field);
            }
            case "invitations" -> {
                pages.getInvitationsPage().insertField(input, field);
                LOGGER.info(input + " was sent to the " + field);
            }
            default -> throw new IllegalArgumentException("No such a page: " + page);
        }

    }

    @When("user clicks {string} button on {string} page")
    public void user_clicks_button_on_page(String button, String page) throws InterruptedException {
        switch (page.toLowerCase().trim()) {
            case "login", "choose account" -> {
                pages.getLoginPage().clickButton(button);
                LOGGER.info(button + " button was clicked on the " +  page + " page");
            }
            case "left navigate" -> {
                Thread.sleep(3000);
                pages.getLeftNavigatePage().clickButton(button);
                LOGGER.info(button + " button was clicked on the " +  page + " page");
            }
            case "received doc" -> {
                pages.getReceivedDocsPage().clickButton(button);
                LOGGER.info(button + " button was clicked on the " +  page + " page");
            }
            case "my uploads" -> {
                pages.getMyUploadsPage().clickButton(button);
                LOGGER.info(button + " button was clicked on the " + page + " page");
            }
            case "invitations" -> {
                pages.getInvitationsPage().clickButton(button);
                LOGGER.info(button + " button was clicked on the " + page + " page");
            }
            default -> throw new IllegalArgumentException("No such a page: " + page);
        }
    }

    @When("user clicks {string} option on {string} dropdown")
    public void user_clicks_option_on_dropdown(String dropDownOption, String dropDown) {
       switch (dropDown.toLowerCase().trim()) {
           case "tags" -> {
               pages.getReceivedDocsPage().clickDropdownOption(dropDownOption);
               LOGGER .info(dropDownOption + " was clicked on the " + dropDown);
           }
           case "upload date" -> {
               pages.getReceivedDocsPage().clickDate(dropDownOption);
               LOGGER.info(dropDownOption + " was clicked on the " + dropDown);
           }
           case "uploaded by" -> {
               pages.getReceivedDocsPage().clickDropdownOption(dropDownOption);
               LOGGER.info(dropDownOption + " was clicked on the " + dropDown);
           }
           case"service" -> {
               pages.getReceivedDocsPage().clickDropdownOption(dropDownOption);
               LOGGER.info(dropDownOption + " was clicked on the " + dropDown);
           }
           default -> throw new IllegalArgumentException("No such a dropdown: " + dropDown);
       }
    }
    @Then("user should see message {string} on {string} page")
    public void user_should_see_message_on_page(String massage, String page) {
       switch (page.toLowerCase().trim()) {
           case "received doc" -> {
               Assert.assertEquals(massage, pages.getReceivedDocsPage().getSearchMassage());
               LOGGER.info("Got " + massage + " massage displayed after Search");
           }
           case "invitations" -> {
               Assert.assertEquals(massage, pages.getInvitationsPage().getSearchMassage());
               LOGGER.info("Got " + massage + " massage displayed after Search");
           }
           default -> throw new IllegalArgumentException("No such a page: " + page);
       }
    }

    @Then("user uploads a document")
    public void user_uploads_a_document() throws Exception {
//        WebElement element = Driver.getDriver().findElement(By.xpath("//input[@type='file']"));
//        element.sendKeys("/Users/Polina/Desktop/untitled.txt");
       // BrowserUtils.uploadFileMac("/Users/Polina/Desktop/untitled.txt");
        //BrowserUtils.uploadFileUsingAppleScript("/Users/Polina/Desktop/untitled.txt");
        BrowserUtils.uploadFileMac2("/Users/Polina/Desktop/untitled.txt");
    }

    @Then("a new tab should open with heading {string}")
    public void a_new_tab_should_open_with_heading(String termsAndConditionsHeading) {
       BrowserUtils.switchWindowAndValidate(Driver.getDriver(), "https://numbersquad.com/service-terms-and-conditions", termsAndConditionsHeading);
    }

    @When("user validates {string} is displayed on {string} dropdown")
    public void user_validates_is_displayed_on_dropdown(String text, String dropdown) {
        switch (dropdown.toLowerCase().trim()) {
            case "upload date" -> Assert.assertEquals(text, pages.getReceivedDocsPage().getMonthYearText());
            default -> throw new IllegalArgumentException("No such a dropdown: " + dropdown);
        }
    }
}

