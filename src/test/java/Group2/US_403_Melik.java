package Group2;

import Utilities.DriverClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class US_403_Melik extends DriverClass {
    @Test(groups = "Smoke")
    @Parameters({"username","password"})
    void logOutTest(@Optional("admin") String username, @Optional("Admin123") String password){
        Actions actions = new Actions(driver);
        driver.get("https://openmrs.org/");
        driver.findElement(By.cssSelector("a[class=\"zak-button\"]")).click();
        driver.findElement(By.xpath("//span[text()=\"Explore OpenMRS 2\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=\"Enter the OpenMRS 2 Demo\"]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.id("Inpatient Ward")).click();
        driver.findElement(By.id("loginButton")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selected-location")));
        WebElement locationCheck = driver.findElement(By.id("selected-location"));
        Assert.assertTrue(locationCheck.isDisplayed());

        driver.findElement(By.linkText("Logout")).click();

        Assert.assertEquals("https://demo.openmrs.org/openmrs/login.htm",driver.getCurrentUrl());



    }
}
