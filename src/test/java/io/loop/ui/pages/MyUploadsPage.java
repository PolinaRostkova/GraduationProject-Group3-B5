package io.loop.ui.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(xpath = "//span[contains(text(),' Upload ')]")
    private WebElement uploadButton;

    public void clickButton(String button) {
        switch (button.toLowerCase().trim()) {
            case "upload documents" -> BrowserUtils.waitForClickable(uploadDocumentsButton, DocuportConstants.LARGE).click();
            case "cancel" -> BrowserUtils.waitForClickable(cancelButton, DocuportConstants.LARGE).click();
            case "upload file" -> BrowserUtils.waitForClickable(uploadFileButton, DocuportConstants.LARGE).click();
            default -> throw new IllegalArgumentException("Button " + button + " not found");
        }
    }
}
