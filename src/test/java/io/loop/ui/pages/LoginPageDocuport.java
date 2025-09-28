package io.loop.ui.pages;

import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPageDocuport extends BaseDocuportPage{

    public LoginPageDocuport() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

    @FindBy(xpath = "//input[@type='text']")
    public WebElement usernameInput;

    @FindBy(xpath = "//input[@type='password']")
    public WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginButton;

    @FindBy(xpath = "//button[@class='text-none body-2 font-weight-medium v-btn v-btn--has-bg theme--light v-size--default success']")
    public WebElement contButton;

    @FindBy(xpath = "//img[@alt='Docuport']")
    public WebElement docuportImage;

    public  void loginDocuport(String role) throws InterruptedException {
        switch (role.toLowerCase()){
            case "advisor" -> {
                usernameInput.sendKeys(DocuportConstants.USERNAME_ADVISOR);
                passwordInput.sendKeys(DocuportConstants.PASSWORD);
                loginButton.click();
            }
            case "supervisor" -> {
                usernameInput.sendKeys(DocuportConstants.USERNAME_SUPERVISOR);
                passwordInput.sendKeys(DocuportConstants.PASSWORD);
                loginButton.click();
            }
            case "employee" -> {
                usernameInput.sendKeys(DocuportConstants.USERNAME_EMPLOYEE);
                passwordInput.sendKeys(DocuportConstants.PASSWORD);
                loginButton.click();
            }
            case "client" -> {
                usernameInput.sendKeys(DocuportConstants.USERNAME_CLIENT);
                passwordInput.sendKeys(DocuportConstants.PASSWORD);
                loginButton.click();
                Thread.sleep(3000);
                contButton.click();
                Thread.sleep(3000);
            }
            default -> throw new IllegalArgumentException("Invalid role");
        }
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
