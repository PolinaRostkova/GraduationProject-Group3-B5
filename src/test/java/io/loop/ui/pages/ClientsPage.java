package io.loop.ui.pages;

import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ClientsPage {
     public ClientsPage() {
         PageFactory.initElements(Driver.getDriver(), this);

     }
        @FindBy(xpath = "//span[@class='body-1' and .='Clients']")
        public WebElement clients;


        @FindBy(xpath = "//button[.//span[normalize-space()='Search']]")
        public WebElement search;

        @FindBy(xpath = "//label[normalize-space()='Name']/following-sibling::input")
        public WebElement nameInput;

        @FindBy(xpath = "//span[@class='v-btn__content' and .=' Search ']")
        public WebElement searchButton;

        @FindBy(xpath = "//table//tbody//tr//td[1]//span[contains(@class,'ml-2')]")
        public List<WebElement> namesInTable;




}
