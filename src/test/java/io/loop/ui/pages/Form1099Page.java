package io.loop.ui.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * this is a page for Form 1099 info
 * @author veronika
 */

public class Form1099Page {

    public Form1099Page () {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[.='Add new contractor']/span")
    public WebElement addNewContractorButton;

    @FindBy(xpath = "//span[.='Individual']")
    public WebElement individualOption;

    @FindBy(xpath = "//label[.='First name']/following-sibling::input")
    public WebElement firstNameInput;

    @FindBy(xpath = "//label[.='SSN or TIN']/following-sibling::input")
    public WebElement ssnTinInput;

    @FindBy(xpath = "//label[.='Address (apt/suit)']/following-sibling::input")
    public WebElement addressInput;

    @FindBy(xpath = "//label[.='State']/following-sibling::input[@type='text']")
    public WebElement stateDropdown;

    @FindBy(xpath = "//div[.='Massachusetts']/div/div")
    public WebElement massachusettsOption;

    @FindBy(xpath = "//label[.='Total payment']/following-sibling::input")
    public WebElement totalPaymentOption;

    @FindBy(xpath = "//label[.='Last name']/following-sibling::input")
    public WebElement lastNameInput;

    @FindBy(xpath = "//label[.='Street address']/following-sibling::input")
    public WebElement streetAddressInput;

    @FindBy(xpath = "//label[.='City']/following-sibling::input")
    public WebElement cityInput;

    @FindBy(xpath = "//label[.='Zip code']/following-sibling::input")
    public WebElement zipCodeInput;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveButton;

    @FindBy(xpath = "//tr/td[contains(., 'Luna')]")
    public WebElement contractorDisplayed;


    /**
     * this is for inserting the passed input into the passed field
     * @param field
     * @param input
     * @author veronika
     */
    public void insertField (String field, String input) {
        switch (field.toLowerCase().trim()) {
            case "first name" -> BrowserUtils.waitForVisibility(firstNameInput, DocuportConstants.LARGE).sendKeys(input);
            case "last name" -> BrowserUtils.waitForVisibility(lastNameInput, DocuportConstants.LARGE).sendKeys(input);
            case "ssn or tin" -> BrowserUtils.waitForVisibility(ssnTinInput, DocuportConstants.LARGE).sendKeys(input);
            case "street address" -> BrowserUtils.waitForVisibility(streetAddressInput, DocuportConstants.LARGE).sendKeys(input);
            case "address (apt/suit)" -> BrowserUtils.waitForVisibility(addressInput, DocuportConstants.LARGE).sendKeys(input);
            case "city" -> BrowserUtils.waitForVisibility(cityInput, DocuportConstants.LARGE).sendKeys(input);
            case "zip code" -> BrowserUtils.waitForVisibility(zipCodeInput, DocuportConstants.LARGE).sendKeys(input);
            case "total payment" -> BrowserUtils.waitForVisibility(totalPaymentOption, DocuportConstants.LARGE).sendKeys(input);
            default -> throw new IllegalArgumentException("No such a field: " + field);
        }
    }


    /**
     * this is for clicking the passed button
     * @param button
     * @author veronika
     */
    public void clickButton(String button) throws InterruptedException {
        switch (button.toLowerCase().trim()) {
            case "save" -> BrowserUtils.waitForClickablility(saveButton, DocuportConstants.LARGE).click();
        }
    }

}
