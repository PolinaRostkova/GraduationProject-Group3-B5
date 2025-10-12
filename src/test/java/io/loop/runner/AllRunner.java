package io.loop.runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "io.loop",
        plugin = {
                "pretty",
                "html:target/html-reports/all-report.html",
                "json:target/json-reports/all-report.json",
                "rerun:target/rerun.txt",
               // "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        publish = false
)
public class AllRunner {
}
