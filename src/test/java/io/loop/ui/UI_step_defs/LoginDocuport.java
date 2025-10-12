package io.loop.ui.UI_step_defs;

import io.cucumber.java.en.*;
import io.loop.ui.pages.LoginPageDocuport;
import io.loop.ui.pages.POM;
import io.loop.utilities.BrowserUtil;
import io.loop.utilities.ConfigurationReader;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class LoginDocuport {

    POM pages = new POM();
    LoginPageDocuport loginPageDocuport = new LoginPageDocuport();

    @Given("user is on Docuport login page")
    public void user_is_on_docuport_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("docuportBETA"));

    }

    @When("user enters username for {}")
    public void user_enters_username_for_role(String roleUsername) throws InterruptedException {
        BrowserUtil.waitForClickablility(loginPageDocuport.usernameInput, 10);
        assertTrue(loginPageDocuport.loginButton.isDisplayed());
        loginPageDocuport.usernameInput.sendKeys(enterUsername(roleUsername));

    }


    @And("user enters password for needed role")
    public void user_enters_password_for_client() {
        loginPageDocuport.passwordInput.sendKeys(DocuportConstants.PASSWORD);
    }


    @And("user clicks login button")
    public void user_clicks_login_button() throws InterruptedException {
        loginPageDocuport.loginButton.click();
    }


    @Then("user should be able to see the home page for {}")
    public void user_should_be_able_to_see_the_home_page_for_role(String role) throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> matches = Driver.getDriver().findElements(By.xpath("//span[.=' Continue ']"));
        if (!matches.isEmpty()) {
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(matches.getFirst())).click();
        }

        assertTrue("User didn't login and can't see the home page", BrowserUtil.waitForVisibility(loginPageDocuport.docuportImage, 10).isDisplayed());
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
