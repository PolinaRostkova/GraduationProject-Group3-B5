package io.loop.ui.UI_step_defs;
import io.cucumber.java.en.*;
import io.loop.ui.pages.POM;
import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Jane_UI01_LoginAndLogOut_stepDefs {

    POM pages = new POM();
    private static final Logger LOG = LogManager.getLogger();


//    @When("user logs in as {string}")
//    public void user_logs_in_as(String role) throws InterruptedException {
//
//        BrowserUtils.waitForClickablility(pages.getLoginPage().usernameInput, DocuportConstants.EXTRA_LARGE);
//        pages.getLoginPage().usernameInput.clear();
//        BrowserUtils.waitFor(2);
//        pages.getLoginPage().usernameInput.sendKeys(pages.getLoginPage().enterUsername(role));
//
//        BrowserUtils.waitForClickablility(pages.getLoginPage().passwordInput, DocuportConstants.EXTRA_LARGE);
//        pages.getLoginPage().passwordInput.clear();
//        pages.getLoginPage().passwordInput.sendKeys(DocuportConstants.PASSWORD);
//        BrowserUtils.clickWithJS(pages.getLoginPage().loginButton);
//        LOG.info("Login clicked for role: {}", role);
//
//    }


    @Then("user should be able to see {string} button on {string} page")
    public void user_should_be_able_to_see_button_on_page(String button, String page) {
        switch (page.toLowerCase().trim()) {
            case "received docs" -> {
                BrowserUtils.waitForVisibility(pages.getLeftNavigatePage().receivedDocsButton, DocuportConstants.LARGE);
                assertTrue(pages.getLeftNavigatePage().receivedDocsButton.isDisplayed());
            }
            case "my uploads" -> {
                BrowserUtils.waitForVisibility(pages.getLeftNavigatePage().myUploads, DocuportConstants.LARGE);
                assertTrue(pages.getLeftNavigatePage().myUploads.isDisplayed());
            }
        }
        LOG.info("Home core UI verified. Button: {} is displayed", button );
    }


    @When("user logs out")
    public void user_logs_out() {
        BrowserUtils.waitForClickablility(pages.getLogOutPage().userIcon, DocuportConstants.MEDIUM).click();
        LOG.info("User icon was clicked");
        BrowserUtils.waitForClickablility(pages.getLogOutPage().logOutButton, DocuportConstants.MEDIUM).click();
        LOG.info("User successfully logged out");
    }


    @Then("browser url should be {string}")
    public void browser_url_should_be(String url) {
        String actualTitle = Driver.getDriver().getCurrentUrl();
        assertEquals("Actual URL DOES NOT MATCH expected", actualTitle,url);
        LOG.info("Actual URL MATCHES expected");
    }


    @When("user refreshes the page")
    public void user_refreshes_the_page() {
        Driver.getDriver().navigate().refresh();
        LOG.info("Page was refreshed");
    }
}
