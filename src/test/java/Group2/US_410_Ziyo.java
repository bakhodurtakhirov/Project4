package Group2;

import Utilities.DriverClassV2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class US_410_Ziyo extends DriverClassV2 {
//1.The user's system timezone is set to a timezone other than UTC+0 (GMT+0).
//2.The user is logged in on the application homepage.
//3.The user attempts to schedule a patient appointment:
//4.Clicks on the [Appointment Scheduling] button.
//5.Clicks on [Manage Appointments].
//6.Enters the ID or name created during patient registration in the search input.
//7.Selects the patient from the displayed list by clicking on them.
//8.The user should see and verify the error message:
//   "Your computer is not set to the right time zone. Please change to Coordinated Universal
//    Time and then close and restart your browser to assure proper scheduling functionality."_
//**Optional Criterion (User Assertion)**:
//    When the user sets their computer system time to GMT+0, they follow the same steps and assert that the error
//    message doesn't appear. ( _Can be considered similar to [US_411]
    @Test
    public void Test2(){
        driver.get("https://demo.openmrs.org/openmrs/login.htm");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("Admin123");
        driver.findElement(By.xpath("//li[text()=\"Inpatient Ward\"]")).click();
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//i[@class=\"icon-calendar\"])[2]")));
        driver.findElement(By.xpath("(//i[@class=\"icon-calendar\"])[2]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[@type=\"button\"])[3]")));
        driver.findElement(By.xpath("(//a[@type=\"button\"])[3]")).click();
        driver.findElement(By.cssSelector("input[type=\"text\"]")).sendKeys("100HNY");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tr[class=\"odd\"]")));
        driver.findElement(By.cssSelector("tr[class=\"odd\"]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p)[5]")));
        WebElement message = driver.findElement(By.xpath("(//p)[5]"));
        Assert.assertTrue(message.isDisplayed());
    }
}
