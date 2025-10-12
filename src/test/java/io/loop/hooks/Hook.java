package io.loop.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.loop.utilities.BrowserUtils;
import io.loop.utilities.BrowserUtils;
import io.loop.utilities.ConfigurationReader;
import io.loop.utilities.DB_Utility;
import io.loop.utilities.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

//import static io.cucumber.jsonformatter.CucumberJvmJson.JvmElementType.scenario;

public class Hook {
    @Before("@ui")
    public void setUp(Scenario scenario) {
        Driver.getDriver();
        BrowserUtils.myScenario = scenario;
        Driver.getDriver().get(ConfigurationReader.getProperty("docuportBETA"));
    }

    @After("@ui")
    public void tearDown(Scenario scenario) {
        // only takes screenshot when scenario is failed
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

       // Driver.closeDriver();
    }

    @Before("@db")
    public void setupDBForDocuport() {
        DB_Utility.docuportCreateConnection();
    }

    @After("@db")
    public void closeConnection(){
        DB_Utility.destroy();
    }
}
