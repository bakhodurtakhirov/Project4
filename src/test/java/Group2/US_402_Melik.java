package Group2;

import Utilities.DriverClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class US_402_Melik extends DriverClass {

    @Test(groups = "Smoke")
    @Parameters({"username","password"})
    void loginSuccessfully(@Optional("admin") String username, @Optional("Admin123") String password){
        driver.get("https://openmrs.org/");
        driver.findElement(By.cssSelector("a[class=\"zak-button\"]")).click();
        driver.findElement(By.xpath("//span[text()=\"Explore OpenMRS 2\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class=\"elementor-button-text\"])[2]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.id("Inpatient Ward")).click();
        driver.findElement(By.id("loginButton")).click();
        WebElement check = driver.findElement(By.xpath("//div[@id=\"content\"]//h4"));
        Assert.assertTrue(check.isDisplayed());

    }
}
