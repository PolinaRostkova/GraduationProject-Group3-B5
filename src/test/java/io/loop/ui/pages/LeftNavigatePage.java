package io.loop.ui.pages;

import io.loop.utilities.BrowserUtil;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * this is a page for Left Navigate info
 * @author veronika
 */

public class LeftNavigatePage {

    public LeftNavigatePage () {
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
     */
    public void clickButton(String button) throws InterruptedException {
        switch (button.toLowerCase().trim()) {
            case "home" -> {
                BrowserUtil.waitForClickable2(homeButton, DocuportConstants.LARGE);
                try {
                    System.out.println("try");
                    homeButton.click();
                } catch (StaleElementReferenceException e) {
                    System.out.println("catch");
                    homeButton.click();
                }
            }
            case "1099 form" -> BrowserUtil.waitForClickablility(form1099, DocuportConstants.LARGE).click();
        }
    }
}

