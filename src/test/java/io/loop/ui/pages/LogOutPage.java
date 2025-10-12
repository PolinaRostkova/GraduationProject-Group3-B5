package io.loop.ui.pages;
import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LogOutPage {
    public LogOutPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//div[@class='v-avatar primary']")
    public WebElement userIcon;

    @FindBy(xpath = "//span[contains(text(),'Log out')]")
    public WebElement logOutButton;


}

