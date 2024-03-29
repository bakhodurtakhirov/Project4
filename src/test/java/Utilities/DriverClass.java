package Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.IOException;
import java.time.Duration;

public class DriverClass {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions actions;

    @BeforeClass(alwaysRun = true)
    @Parameters("browserName")
    public void createDriver(@Optional("chrome") String browser){

        switch (browser.toLowerCase()){
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
        }
        driver.manage().window().maximize();

        driver.get("https://openmrs.org/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);


    }
    @AfterClass(alwaysRun = true)
    public void quitDriver(){
        driver.quit();
        closePreviousDrivers();
    }

    public void closePreviousDrivers(){
        try {
            if (Platform.getCurrent().is(Platform.MAC)) {
                Runtime.getRuntime().exec("pkill -f chromedriver");
            } else if (Platform.getCurrent().is(Platform.WINDOWS)) {
                Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static final Logger logger = LogManager.getLogger();
    @BeforeMethod(alwaysRun = true)
    public void LogsBeforeTest(ITestResult testResult){
//        System.out.println("Test has started");
        //all > trace > debug > info > warn > error > fatal
        logger.always();
        logger.trace("Trace level log");
        logger.debug("Debug level log");
        logger.info("Info level log");
        logger.warn("Warn level log");
        logger.error("Error level log");
        logger.fatal("Fatal level log");
//        logger.info(testResult.getMethod().getMethodName() + " test has started");
    }
    @AfterMethod(alwaysRun = true)
    public void LogsAfterTest(ITestResult testResult){
//        System.out.println("Test has ended");
//        logger.info(testResult.getMethod().getMethodName() + " has ended with status of " + testResult.getStatus());
    }
}
