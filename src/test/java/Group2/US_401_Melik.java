package Group2;

import Utilities.DriverClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class US_401_Melik extends DriverClass {

    @Test(groups = "Smoke")
    void login() {
        driver.get("https://openmrs.org/");
        driver.findElement(By.cssSelector("a[class=\"zak-button\"]")).click();
        driver.findElement(By.xpath("//span[text()=\"Explore OpenMRS 2\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\"Enter the OpenMRS 2 Demo\"]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin12345");
        driver.findElement(By.id("loginButton")).click();
        WebElement errorLocation = driver.findElement(By.id("sessionLocationError"));
        Assert.assertTrue(errorLocation.isDisplayed(), "You must Choose location");
        driver.findElement(By.id("Inpatient Ward")).click();
        driver.findElement(By.id("loginButton")).click();
        WebElement errorMessage = driver.findElement(By.id("error-message"));

        Assert.assertTrue(errorMessage.isDisplayed());
    }
}
