package io.loop.ui.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * this is a page for Left Navigate info
 * @author veronika
 * @author halina
 */

public class LeftNavigatePage {
    public LeftNavigatePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    POM pages = new POM();


    @FindBy(xpath = "//span[contains(text(),'Upload')]")
    public WebElement uploadButton;

    @FindBy(xpath = "//a[@href='/clients']")
    public WebElement clientsButton;

    @FindBy(xpath = "//a[@href='/leads']")
    public WebElement leadsButton;

    @FindBy(xpath = "//a[@href='/users']")
    public WebElement usersButton;

    @FindBy(xpath = "//span[contains(text(),'Home')]")
    public WebElement homeButton;

    @FindBy(xpath = "//span[contains(text(),'Received')]")
    public WebElement receivedDocsButton;

    @FindBy(xpath = "//span[contains(text(),'My uploads')]")
    public WebElement myUploads;

    @FindBy(xpath = "//span[contains(text(),'Invitations')]")
    public WebElement invitationsButton;


    @FindBy(xpath = "//a[@href='/bookkeeping']")
    public WebElement bookkeepingButton;

    @FindBy(xpath = "//span[contains(text(),'1099 Form')]")
    public WebElement form1099;

    @FindBy(xpath = "//span[contains(text(),'Reconciliations')]")
    public WebElement reconciliations;

    @FindBy(xpath = "//a[contains(text(),'Terms')]")
    public WebElement termsAndConditionsButton;


    @FindBy(xpath = "//div[@class='v-list-item__title']")
    public List<WebElement> modulesForEachRole;

    // Expected modules names for each role
    List<String> expectedModulesClient = List.of("Home", "Received docs", "My uploads", "Invitations", "1099 Form", "Reconciliations");
    List<String> expectedModulesAdvisor = List.of("Home", "Received docs", "My uploads", "Clients", "Invitations", "Users", "Leads", "Bookkeeping", "1099 Form", "Reconciliations");
    List<String> expectedModulesSupervisor = List.of("Home", "Received docs", "My uploads", "Clients", "Users", "Leads", "Bookkeeping", "1099 Form", "Reconciliations");
    List<String> expectedModulesEmployee = List.of("Home", "Received docs", "My uploads", "Clients", "Users", "Bookkeeping", "1099 Form", "Reconciliations");


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


    /**
     * Retrieves the text of all module menu items visible for the role.
     * author HalinaBartasevich
     */
    public List<String> getActualMenuItems() {
        List<String> actualModules = new ArrayList<>();
        for (WebElement module : modulesForEachRole) {
            actualModules.add(module.getText().trim());
        }
        return actualModules;
    }

    /**
     * Retrieves the text of all module menu items visible for the role.
     * author HalinaBartasevich
     */
    public List<String> getExpectedModules(String role) {
        return switch (role.toLowerCase().trim()) {
            case "client" -> expectedModulesClient;
            case "advisor" -> expectedModulesAdvisor;
            case "supervisor" -> expectedModulesSupervisor;
            case "employee" -> expectedModulesEmployee;
            default -> throw new IllegalArgumentException("Unknown role: " + role);
        };
    }

    /**!
     * Validates module menu items for the given user role
     * author HalinaBartasevich
     */
    public void validateMenuItems(String role) {
        List<String> expected = getExpectedModules(role);
        List<String> actual = getActualMenuItems();
        assertEquals(expected, actual);
    }


    /**
     * Clicks each module for the given role and validates its header
     * author HalinaBartasevich
     */
    public void clickAndValidateModulesForRole(String role) {
        List<String> expectedModules;

        switch (role.toLowerCase()) {
            case "advisor" -> expectedModules = expectedModulesAdvisor;
            case "employee" -> expectedModules = expectedModulesEmployee;
            case "supervisor" -> expectedModules = expectedModulesSupervisor;
            case "client" -> expectedModules = expectedModulesClient;
            default -> throw new IllegalArgumentException("No expected modules defined for role: " + role);
        }
        for (String module : expectedModules) {
            System.out.println("Clicking module: " + module);
            clickEachModule(module);

            String actualHeader = pages.getBaseDocuportPage().getHeaderTextFromModules(module).getText().trim();
            assertEquals("Header mismatch for module: " + module, module, actualHeader);
            System.out.println("Header validated for module: " + module + " -> " + actualHeader);
        }
    }

    /**
     * Clicks each module for the given role
     * author HalinaBartasevich
     */

    public void clickEachModule(String button) {
        switch (button.toLowerCase().trim()){
            case "home" -> {
                try {
                    BrowserUtils.waitForClickablility(homeButton, DocuportConstants.LARGE).click();
                } catch (StaleElementReferenceException se) {
                    BrowserUtils.waitForClickablility(homeButton, DocuportConstants.LARGE).click();
                }
            }
            case "upload" ->  BrowserUtils.waitForClickablility(uploadButton, DocuportConstants.LARGE).click();
            case "received docs" -> BrowserUtils.waitForClickablility(receivedDocsButton, DocuportConstants.LARGE).click();
            case "my uploads" -> BrowserUtils.waitForClickablility(myUploads, DocuportConstants.LARGE).click();
            case "invitations" -> BrowserUtils.waitForClickablility(invitationsButton, DocuportConstants.LARGE).click();
            case "1099 form" -> BrowserUtils.waitForClickablility(form1099, DocuportConstants.LARGE).click();
            case "reconciliations" -> BrowserUtils.waitForClickablility(reconciliations, DocuportConstants.LARGE).click();
            case "users" -> BrowserUtils.waitForClickablility(usersButton, DocuportConstants.LARGE).click();
            case "leads" -> BrowserUtils.waitForClickablility(leadsButton, DocuportConstants.LARGE).click();
            case "bookkeeping" -> BrowserUtils.waitForClickablility(bookkeepingButton, DocuportConstants.LARGE).click();
            case "clients" -> BrowserUtils.waitForClickablility(clientsButton, DocuportConstants.LARGE).click();
            case "terms and conditions" -> BrowserUtils.waitForClickablility(termsAndConditionsButton, DocuportConstants.LARGE).click();
            default -> throw new IllegalArgumentException("Not such a button: " + button);
        }
    }
}
