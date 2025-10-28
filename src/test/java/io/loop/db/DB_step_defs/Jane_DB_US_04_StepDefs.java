package io.loop.db.DB_step_defs;

import io.cucumber.java.en.*;
import io.loop.ui.pages.POM;
import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DB_Utility;
import io.loop.utilities.DocuportConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.Assert.assertTrue;

public class Jane_DB_US_04_StepDefs {
    POM pages = new POM();
    private static final Logger LOG = LogManager.getLogger();
    List<String> expectedNames;



    @Then("the user navigates to {string} page")
    public void the_user_navigates_to_page(String page) {
        BrowserUtils.waitForClickable(pages.getClientsPage().clients, DocuportConstants.SMALL);
        pages.getClientsPage().clients.click();

    }

    @Then("the user searches names")
    public void the_user_searches_names(List<String> names) {
//        BrowserUtils.waitForVisibility(pages.getClientsPage().search);
//        pages.getClientsPage().search.click();
//        for (String name : names) {
//
//            BrowserUtils.waitFor(2);
//
//            pages.getClientsPage().nameInput.sendKeys(name);
//            pages.getClientsPage().searchButton.click();
//
//            BrowserUtils.waitFor(3);
//
//            pages.getClientsPage().searchButton.click();
//            Driver.getDriver().navigate().refresh();
//            pages.getClientsPage().search.click();
//
//            BrowserUtils.waitFor(2);
//        }
        expectedNames = names;
        List<String> uiNames = pages.getClientsPage().namesInTable.stream()
                .map(e -> e.getText().trim())
                .collect(Collectors.toList());

        for(String expectedName : expectedNames) {
            boolean found = uiNames.contains(expectedName);
            assertTrue("Name DID NOT FOUND in the table: " + expectedName, found);
            LOG.info("The name is visible in the UI: " + expectedNames);
        }


    }
    @Then("the user should see names in the database")
    public void the_user_should_see_names_in_the_database() {
        String namesInDBTable = expectedNames.stream()
                .map(name -> "'" + name + "'")
                .collect(Collectors.joining(", "));

        String sql = "SELECT concat(first_name , ' ', last_name) AS name\n" +
                     "FROM document.clients\n" +
                     "WHERE CONCAT(first_name , ' ', last_name) IN (" + namesInDBTable + ")";

        DB_Utility.runQuery(sql);
        List<String> dbNames = DB_Utility.getColumnDataAsList("name");
        for (String expected : expectedNames) {
            Assert.assertTrue(
                    "The name is not found in the database. " + expected, dbNames.contains(expected));
            LOG.info("The name is visible in the DB: " + expected);
        }

    }
}
