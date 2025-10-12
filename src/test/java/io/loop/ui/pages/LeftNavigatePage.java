package io.loop.ui.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * this is a page for Left Navigate info
 * @author veronika
 */

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

    @FindBy(xpath = "//span[contains(text(),'1099 Form')]")
    public WebElement form1099;

    @FindBy(xpath = "//span[contains(text(),'Reconciliations')]")
    public WebElement reconciliations;

    @FindBy(xpath = "//a[contains(text(),'Terms')]")
    public WebElement termsAndConditionsButton;

    /**
     * this is for clicking the passed button
     * @param button
     * @author veronika
     * @author polina
     */
    public void clickButton(String button) {
        switch (button.toLowerCase().trim()) {
            case "home" -> {
                BrowserUtils.waitForClickable2(homeButton, DocuportConstants.LARGE);
                try {
                    System.out.println("try");
                    homeButton.click();
                } catch (StaleElementReferenceException e) {
                    System.out.println("catch");
                    homeButton.click();
                }
            }
            case "upload" -> BrowserUtils.waitForClickable(uploadButton, DocuportConstants.LARGE).click();
            case "received doc" ->  BrowserUtils.waitForClickable(receivedDocsButton, DocuportConstants.LARGE).click();
            case "invitations" ->   BrowserUtils.waitForClickable(invitationsButton, DocuportConstants.LARGE).click();
            case "terms and conditions" -> BrowserUtils.waitForClickable(termsAndConditionsButton, DocuportConstants.LARGE).click();
            case "my uploads" ->  BrowserUtils.waitForClickable(myUploads, DocuportConstants.LARGE).click();
            case "1099 form" -> BrowserUtils.waitForClickablility(form1099, DocuportConstants.LARGE).click();
            default -> throw new IllegalArgumentException("Button " + button + " not found");
        }
    }

    public void clickMenuItem(String itemName) {
        WebElement button = Driver.getDriver().findElement(By.xpath("//span[@class='body-1' and .='"+itemName+"']"));
        button.click();
    }
}
