package io.loop.ui.UI_step_defs;

import io.cucumber.java.en.Then;
import io.loop.ui.pages.POM;
import io.loop.utilities.BrowserUtils;
import io.loop.utilities.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class DocuportGeneralStepDefs {
    private static final Logger LOGGER = LogManager.getLogger(Polina_UI03_StepDefs.class);
    POM pages = new POM();
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

}
