package io.loop.ui.UI_step_defs;

import io.cucumber.java.en.*;
import io.loop.ui.pages.LoginPageDocuport;
import io.loop.ui.pages.POM;
import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class LoginDocuportStepDefs {

    POM pages = new POM();
    LoginPageDocuport loginPageDocuport = new LoginPageDocuport();

    @Given("user is on Docuport login page")
    public void user_is_on_docuport_login_page() {
        Driver.getDriver().get(DocuportConstants.DOCUPORT_TEST);

    }

    @When("user is logged in for {}")
    public void user_enters_username_for_role(String roleUsername) throws InterruptedException {
        BrowserUtils.waitForClickablility(loginPageDocuport.usernameInput, 10);
        loginPageDocuport.loginDocuport(roleUsername);

    }



    @Then("user should be able to see the home page for {}")
    public void user_should_be_able_to_see_the_home_page_for_role(String role) throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> matches = Driver.getDriver().findElements(By.xpath("//span[.=' Continue ']"));
        if (!matches.isEmpty()) {
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(matches.getFirst())).click();
        }

        assertTrue("User didn't login and can't see the home page", BrowserUtils.waitForVisibility(loginPageDocuport.docuportImage, 10).isDisplayed());
    }


    public String enterUsername(String username) throws InterruptedException {

        return switch (username.toLowerCase()){
            case "client" -> DocuportConstants.USERNAME_CLIENT;
            case "employee" -> DocuportConstants.USERNAME_EMPLOYEE;
            case "supervisor" -> DocuportConstants.USERNAME_SUPERVISOR;
            case "advisor" -> DocuportConstants.USERNAME_ADVISOR;
            default -> throw new IllegalArgumentException("Invalid username: " + username.toLowerCase());
        };

    }

}
