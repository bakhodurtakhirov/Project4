package Group2;

import Utilities.DriverClassV2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class US_409_Ziyo extends DriverClassV2 {
//1.The doctor, logged in as [*admin*] on the application homepage.
//2.The doctor performs the patient registration process described in [*US_404*] twice and saves the obtained IDs (ID1 and ID2).
//3.The doctor is on the application homepage.
//4.The doctor clicks on the [*Data Management*] link.
//5.The doctor clicks on the [*Merge Patient Electronic Records*] button.
//6.The doctor enters the values ID1 and ID2 into the two consecutive Patient ID fields.
//7.The doctor clicks the [*Continue*] button.
//8.The doctor sees a warning: "Merging cannot be undone! Please check records before continuing."
//9.The doctor selects which patient record to merge (clicks).
//10. The doctor clicks the [*Yes, Continue*] button.
//11. The doctor sees the IDs **_ID1_** and **_ID2_** in the top right corner under the Patient ID.

    @Test
    public void Test() {
        driver.get("https://demo.openmrs.org/openmrs/login.htm");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("Admin123");
        driver.findElement(By.xpath("//li[text()=\"Inpatient Ward\"]")).click();
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[@class=\"icon-hdd\"]")));
        driver.findElement(By.xpath("//i[@class=\"icon-hdd\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[@class=\"icon-group\"]")));
        driver.findElement(By.xpath("//i[@class=\"icon-group\"]")).click();
        driver.findElement(By.xpath("(//input[@class=\"form-control input-sm input-lg\"])[1]")).sendKeys("100J9R");
        driver.findElement(By.xpath("(//input[@class=\"form-control input-sm input-lg\"])[2]")).sendKeys("100JAN");
        actions.keyDown(Keys.ENTER);
       WebElement clickButton =  driver.findElement(By.xpath("//input[@value=\"Continue\"]"));
       actions.click(clickButton).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
        WebElement message = driver.findElement(By.xpath("//h1"));
        Assert.assertTrue(message.isDisplayed());

        WebElement patient1 = driver.findElement(By.xpath("//div[@id=\"first-patient\"]"));
        patient1.click();

        driver.findElement(By.cssSelector("input[value=\"Yes, continue\"]")).click();

        List<WebElement> patients = driver.findElements(By.xpath("//div[@class=\"float-sm-right\"]//span"));
        for (WebElement patient: patients){
            Assert.assertTrue(patient.isDisplayed());
        }

    }

}

