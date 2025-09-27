package io.loop.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {
    // creating a private constructor we are closing access to the object of this class from outside the class

    private Driver() {

    }

    // making our driver instance private, so it is not reachable outside the class
    // we make it static because we want it to run before everything else, and we will use it in static method

    private static InheritableThreadLocal <WebDriver> driverPool = new InheritableThreadLocal<>();


    // creating a reusable method that will run the same driver instance every time we call it

    /**
     * Singleton Patter
     * @return
     */

    public static WebDriver getDriver() {
        if (driverPool.get() == null) {
            String browserType = ConfigurationReader.getProperty("browser");
            ChromeOptions options = new ChromeOptions();
            switch (browserType.toLowerCase()) {
                case "chrome" -> {
                    options.addArguments("--disable-blink-features=AutomationControlled");
                    driverPool.set(new ChromeDriver(options));
                }
                case "firefox" -> driverPool.set(new FirefoxDriver());
                case "safari" -> driverPool.set(new SafariDriver());
                case "headless" -> {
                    options.addArguments("--disable-blink-features=AutomationControlled");
                    options.addArguments("--headless");
                    driverPool.set(new ChromeDriver(options));
                }
            }

            driverPool.get().manage().window().maximize();
            driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driverPool.get();
    }

    /**
     * Closing the driver
     * @author Polina
     */

    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove(); // we assign it back to null so that next time we can use it an assign
        }
    }
}
