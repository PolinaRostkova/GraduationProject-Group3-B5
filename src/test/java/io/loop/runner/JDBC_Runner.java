package io.loop.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
               // "pretty",
                "html:target/html-reports/cucumber-reports.html",
                "rerun:target/rerun.txt",
                "json:target/json-reports/json-report.json",
                //"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        },
        features = "classpath:features/db",
        glue = "io.loop",
        dryRun = false,
        tags = "@db",
        monochrome = true,
        publish = false
)
public class JDBC_Runner {
}
