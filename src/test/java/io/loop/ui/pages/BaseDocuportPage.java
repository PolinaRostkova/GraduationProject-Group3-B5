package io.loop.ui.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseDocuportPage {
    public BaseDocuportPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

    // methods created for this homework with click button
    public String getMenuItemText(String itemName) {
        WebElement menuItem = Driver.getDriver().findElement(By.xpath("//span[.='" + itemName + "']"));
        return menuItem.getText();
    }

    public void clickMenuItem(String itemName)  {
        WebElement button = Driver.getDriver().findElement(By.xpath("//span[@class='body-1' and .='"+itemName+"']"));
        button.click();
    }

    public WebElement getItemsFromThisPage(String itemName) {
        return Driver.getDriver().findElement(By.xpath("//span[@class='subtitle-2 text-none' and .='" + itemName + "']"));
    }

    public WebElement getHeaderTextFromModules(String moduleName) {
        return Driver.getDriver().findElement(By.xpath("//span[.='" + moduleName + "']")); // Halina: update xpath to //span (instead of h1)
    }
}
