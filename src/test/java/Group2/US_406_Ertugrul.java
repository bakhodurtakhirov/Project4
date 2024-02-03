package Group2;

import Utilities.DriverClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.List;

public class US_406_Ertugrul extends DriverClass {

    //  Acceptance Criteria (AC_06)
//    1. The doctor, logged in as [admin] on the application homepage.
//    2. The doctor clicks on the [Find Patient Record] button.
//    3. The doctor successfully searches for a patient using information such as name, surname, or ID.
//    4. The doctor should be able to view and click on the search result.
//    5. When clicking on the search result, the doctor should be able to access the patient's medical history, appointments, and other information.
//    6. If the doctor attempts to search for a non-existent patient, they receive a warning message: "No matching records found" -NEGATIVE-.

    @Test
    void US_406_PatientSearch() throws InterruptedException {

        driver.findElement(By.linkText("Demo")).click();
        driver.findElement(By.xpath("//span[text()=\"Explore OpenMRS 2\"]")).click();
        driver.findElement(By.xpath("//span[text()=\"Enter the OpenMRS 2 Demo\"]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("Admin123");
        List<WebElement> locationList = driver.findElements(By.cssSelector("ul[id=\"sessionLocation\"]>li"));
        int randomIndex = (int) (Math.random() * locationList.size());
        locationList.get(randomIndex).click();
        driver.findElement(By.id("loginButton")).click();

        WebElement element = driver.findElement(By.xpath("//a[@id='coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension']"));
        element.click();

        WebElement inputElement = driver.findElement(By.xpath("//input[@id='patient-search']"));
        inputElement.click();
        inputElement.sendKeys("erneste");

        String searchText = "erneste BYIRINGIRO";
        WebElement tdElement = driver.findElement(By.xpath("//td[text()='" + searchText + "']"));
        tdElement.click();

        Thread.sleep(3000);

        String hrefValue = "/openmrs/index.htm";
        WebElement aElement = driver.findElement(By.xpath("//a[@href='" + hrefValue + "']"));
        aElement.click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[@id='coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension']")).click();

        WebElement inputElement1 = driver.findElement(By.xpath("//input[@id='patient-search']"));
        inputElement1.click();
        inputElement1.sendKeys("100J51");

        driver.findElement(By.xpath("//td[text()='Marie Pasio']")).click();
        Thread.sleep(3000);

        String hrefValue1 = "/openmrs/index.htm";
        WebElement aElement1 = driver.findElement(By.xpath("//a[@href='" + hrefValue + "']"));
        aElement1.click();
        driver.findElement(By.xpath("//a[@id='coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension']")).click();


        WebElement inputElement2 = driver.findElement(By.xpath("//input[@id='patient-search']"));
        inputElement2.click();
        inputElement2.sendKeys("100J511");
        Thread.sleep(3000);



    }

}
