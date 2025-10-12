package io.loop.ui.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InvitationsPage {
    public InvitationsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[@class='subtitle-2 text-none' and .='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//span[@class='v-btn__content' and .=' Search ']")
    private WebElement searchSubmitButton;

    @FindBy(xpath = "//label[.='Recipient']/following-sibling::input")
    private WebElement recipientInput;

    @FindBy(xpath = "//label[@class='v-label theme--light' and .='Sent']")
    private WebElement sentRadioButton;

    @FindBy(xpath = "//p[@class='mb-0 subtitle-1']")
    private WebElement searchMassage;

    public void clickButton(String button) {
        switch (button.toLowerCase().trim()) {
            case "search" -> BrowserUtils.waitForClickable(searchButton, DocuportConstants.LARGE).click();
            case "sent" ->  BrowserUtils.waitForClickable(sentRadioButton, DocuportConstants.SMALL).click();
            case "search submit" -> BrowserUtils.waitForClickable(searchSubmitButton, DocuportConstants.LARGE).click();
            default -> throw new IllegalArgumentException("No such button: " + button);
        }
    }

    public void insertField(String input, String field) {
        switch (field.toLowerCase().trim()) {
            case "recipient" -> BrowserUtils.waitForClickable(recipientInput, DocuportConstants.LARGE).sendKeys(input);
            default -> throw new IllegalArgumentException("No such field: " + field);
        }
    }

    public String getSearchMassage() {
        return searchMassage.getText();
    }

}
