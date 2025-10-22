package io.loop.ui.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class MyUploadsPage {

    public MyUploadsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[.='Upload documents' and @class='subtitle-2 text-none']")
    private WebElement uploadDocumentsButton;

    @FindBy(xpath = "//span[.=' Cancel ']")
    private WebElement cancelButton;

    @FindBy(xpath = "//span[.='Upload file' and @class='subtitle-2 text-none']")
    private WebElement uploadFileButton;

    @FindBy(xpath = "//label[.='Client']/following-sibling::input[@type='text']")
    private WebElement clientNameInput;

    @FindBy(xpath = "//label[.='Service']/following-sibling::input[@type='text']")
    private WebElement serviceNameInput;

    @FindBy(xpath = "//div[.='Alita Effertz']/div/div")
    private WebElement alitaEffertzInput;

    @FindBy(xpath = "//div[.='Consultancy']/div/div/div/div")
    private WebElement consultancyInput;

    @FindBy(xpath = "//span[@class='ma-1 v-chip v-chip--clickable v-chip--label v-chip--outlined theme--dark v-size--small']/span")
    private WebElement irsStateLetterTag;

    @FindBy(xpath = "//span[@class='ma-1 v-chip v-chip--clickable v-chip--label v-chip--outlined theme--dark v-size--small']/span[.=' Q1 ']")
    private WebElement q1Tag;

    @FindBy(xpath = "//span[contains(text(),' Upload ')]")
    private WebElement uploadButton;

    @Getter
    @FindBy(xpath = "//input[@type='file']")
    private WebElement fileInput;

    public void clickButton(String button) {
        switch (button.toLowerCase().trim()) {
            case "upload documents" ->
                    BrowserUtils.waitForClickable(uploadDocumentsButton, DocuportConstants.LARGE).click();
            case "cancel" -> BrowserUtils.waitForClickable(cancelButton, DocuportConstants.LARGE).click();
            case "upload file" -> BrowserUtils.waitForClickable(uploadFileButton, DocuportConstants.LARGE).click();
            case "client name" -> BrowserUtils.waitForClickable(clientNameInput, DocuportConstants.LARGE).click();
            case "service name" -> BrowserUtils.waitForClickable(serviceNameInput, DocuportConstants.LARGE).click();
            case "upload" -> BrowserUtils.waitForClickable(uploadButton, DocuportConstants.LARGE).click();
            case "irs/state letter" ->
                    BrowserUtils.waitForClickable(irsStateLetterTag, DocuportConstants.SMALL).click();
            case "q1" -> BrowserUtils.waitForClickable(q1Tag, DocuportConstants.SMALL).click();
            default -> throw new IllegalArgumentException("Button " + button + " not found");
        }
    }

    public void clickDropdownOption(String option) {
        switch (option.toLowerCase().trim()) {
            case "alita effertz" -> BrowserUtils.waitForClickable(alitaEffertzInput, DocuportConstants.LARGE).click();
            case "consultancy" -> BrowserUtils.waitForClickable(consultancyInput, DocuportConstants.LARGE).click();
            default -> throw new IllegalArgumentException("Dropdown option " + option + " not found");
        }
    }

    public void validateUploadDocument(String documentName) {
        WebElement polinaTestDocument = Driver.getDriver().findElement(By.xpath("//span[.='"+ documentName +"']"));
        BrowserUtils.waitForVisibility(polinaTestDocument, DocuportConstants.LARGE);
        assertTrue(polinaTestDocument.isDisplayed());
    }

    public void uploadHiddenFile(WebDriver driver, WebElement inputLocator, String absolutePath) {
        if (driver instanceof org.openqa.selenium.remote.RemoteWebDriver) {
            ((org.openqa.selenium.remote.RemoteWebDriver) driver)
                    .setFileDetector(new org.openqa.selenium.remote.LocalFileDetector());
        }
        ((JavascriptExecutor) Driver.getDriver()).executeScript(
                "arguments[0].classList.remove('d-none');" +
                        "arguments[0].style.display='block'; arguments[0].style.visibility='visible'; arguments[0].style.opacity=1;",
                inputLocator
        );
        inputLocator.sendKeys(absolutePath);
    }

}
