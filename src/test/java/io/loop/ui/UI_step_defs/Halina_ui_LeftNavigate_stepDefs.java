package io.loop.ui.UI_step_defs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.ui.pages.POM;
import io.loop.utilities.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class Halina_ui_LeftNavigate_stepDefs {

    POM pages = new POM();
    private static final Logger LOG = LogManager.getLogger();
   // WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

    @When("user logs in as {string}")
    public void user_logs_in_as_role(String username) throws InterruptedException {
        pages.getLoginPage().loginDocuport(username);
        LOG.info(username + " logged in");
    }

    @Then("user validates {string} from menu for role")
    public void userValidatesFromMenuForRole(String role) {
        pages.getHalina_LeftModuleNavigatePage().validateMenuItems(role);
        LOG.info("Expected modules = Actual modules for : " + role);
    }

    @And("user clicks and validates all modules for {string}")
    public void userClicksAndValidatesAllModulesFor(String role) {
        pages.getHalina_LeftModuleNavigatePage().clickAndValidateModulesForRole(role);
        Driver.closeDriver();
    }
}







