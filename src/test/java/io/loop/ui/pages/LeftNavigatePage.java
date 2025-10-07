package io.loop.ui.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeftNavigatePage {
    public LeftNavigatePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[contains(text(),'Upload')]")
    public WebElement uploadButton;

    @FindBy(xpath = "//span[contains(text(),'Home')]")
    public WebElement homeButton;

    @FindBy(xpath = "//span[contains(text(),'Received')]")
    public WebElement receivedDocsButton;

    @FindBy(xpath = "//span[contains(text(),'My uploads')]")
    public WebElement myUploads;

    @FindBy(xpath = "//span[contains(text(),'Invitations')]")
    public WebElement invitationsButton;

    @FindBy(xpath = "//a[.=' Terms and conditions ']")
    public WebElement termsAndConditionsButton;

    public void clickButton(String buttonText) {
        switch (buttonText.toLowerCase().trim()) {
            case "home" -> BrowserUtils.waitForClickable(homeButton, DocuportConstants.LARGE).click();
            case "upload" -> BrowserUtils.waitForClickable(uploadButton, DocuportConstants.LARGE).click();
            case "received doc" ->  BrowserUtils.waitForClickable(receivedDocsButton, DocuportConstants.LARGE).click();
            case "invitations" ->   BrowserUtils.waitForClickable(invitationsButton, DocuportConstants.LARGE).click();
            case "terms and conditions" -> BrowserUtils.waitForClickable(termsAndConditionsButton, DocuportConstants.LARGE).click();
            case "my uploads" ->  BrowserUtils.waitForClickable(myUploads, DocuportConstants.LARGE).click();
            default -> throw new IllegalArgumentException("Button " + buttonText + " not found");
        }
    }

    public void clickMenuItem(String itemName) {
        WebElement button = Driver.getDriver().findElement(By.xpath("//span[@class='body-1' and .='"+itemName+"']"));
        button.click();
    }
}
