package io.loop.runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features/ui",
        glue = "io.loop",           // or "io.loop.ui" only for UI
        tags = "@left_navigation",
        dryRun = false,              // turn off after it compiles
        plugin = {
                //"pretty",
                "html:target/html-reports/ui-report.html",
                "json:target/json-reports/ui-report.json",
                "rerun:target/rerun.txt"
                // add the Extent adapter back after the POM fix below
                // "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        publish = false
)
public class UI_CukesRunner {

}
