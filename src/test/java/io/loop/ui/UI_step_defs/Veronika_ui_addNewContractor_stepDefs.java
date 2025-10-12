package io.loop.ui.UI_step_defs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.ui.pages.POM;
import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.assertTrue;

public class Veronika_ui_addNewContractor_stepDefs {

    private static final Logger LOG = LogManager.getLogger();
    POM pages = new POM();

    @When("user inserts {string} to {string} field on {string} page")
    public void user_inserts_to_field_on_page(String input, String field, String page) {
        switch (page.toLowerCase().trim()) {
            case "login" -> {
                pages.getLoginPage().insertField(field, input);
                LOG.info(input + " - was successfully sent to - " + field);
            }
        }
    }

    @When("user clicks {string} button on {string} page")
    public void user_clicks_button_on_page(String button, String page) throws InterruptedException {
        switch (page.toLowerCase().trim()) {
            case "login", "choose account" -> {
                pages.getLoginPage().clickButton(button);
                LOG.info(button + " - was successfully clicked");
            }
            case "left navigate" -> {
                pages.getLeftNavigatePage().clickButton(button);
                LOG.info(button + " - was successfully clicked");
            }
            case "1099 form" -> {
                pages.getForm1099Page().clickButton(button);
                LOG.info(button + " - was successfully clicked");
            }
            default -> throw new IllegalArgumentException("No such a page: " + page);
        }
    }

    @Then("user adds new contractor")
    public void user_adds_new_contractor() {
        pages.getForm1099Page().addNewContractorButton.click();
        pages.getForm1099Page().individualOption.click();
    }

    @Then("user inserts {string} to {string} field")
    public void user_inserts_to_field(String input, String field) {
        switch (field.toLowerCase().trim()) {
            case "first name", "last name", "ssn or tin", "street address", "address (apt/suit)", "city", "zip code",
                 "total payment" -> {
                pages.getForm1099Page().insertField(field, input);
                LOG.info(input + " - was successfully sent to - " + field);
            }
            default -> throw new IllegalArgumentException("No such a field: " + field);
        }
    }

    @Then("user selects {string} on {string} field")
    public void user_selects_to_field(String option, String field) throws InterruptedException {
        switch (field.toLowerCase().trim()) {
            case "state" -> {
                BrowserUtils.waitForClickablility(pages.getForm1099Page().stateDropdown, DocuportConstants.LARGE).click();
                Thread.sleep(3000);
                BrowserUtils.waitForVisibility(pages.getForm1099Page().stateDropdown, DocuportConstants.LARGE).sendKeys(option);
                BrowserUtils.waitForClickablility(pages.getForm1099Page().massachusettsOption, DocuportConstants.LARGE).click();
                LOG.info("Massachusetts - was successfully sent to - " + field);
            }
            default -> throw new IllegalArgumentException("No such a field: " + field);
        }
    }

    @Then("user should see the contractor displayed in the list")
    public void user_should_see_the_contractor_displayed_in_the_list() throws InterruptedException {
          assertTrue(pages.getForm1099Page().contractorDisplayed.isDisplayed());
          LOG.info("contractor" + pages.getForm1099Page().contractorDisplayed.getText() + " - is displayed");
    }

}
