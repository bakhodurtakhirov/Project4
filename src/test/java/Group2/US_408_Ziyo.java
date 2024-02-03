package Group2;

import Utilities.DriverClassV2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class US_408_Ziyo extends DriverClassV2 {
//1.The user is logged in on the application homepage.
//2.The user clicks on the [*Find Patient Record*] button.
//3.The user should see and verify that the total number (Z)
// displayed in the format [*Showing X to Y of Z entries*] at the bottom
// of the patient table is the same as the number of rows in the patient table.
    @Test
    public void Test(){
        driver.get("https://demo.openmrs.org/openmrs/login.htm");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("Admin123");
        driver.findElement(By.xpath("//li[text()=\"Inpatient Ward\"]")).click();
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("i[class=\"icon-search\"]")));
        driver.findElement(By.cssSelector("i[class=\"icon-search\"]")).click();
        wait.until(ExpectedConditions.urlContains("findPatient"));

        List<WebElement> rows = driver.findElements(By.xpath("//tbody[@role=\"alert\"]//tr"));
        int rowNumbers = rows.size();


        WebElement entriesNumber = driver.findElement(By.xpath("//div[@class=\"dataTables_info\"]"));
        String entriesText = entriesNumber.getText();
        int totalEntries = extractTotalEntries(entriesText);
        Assert.assertEquals(rowNumbers,totalEntries,"Test is failed");
        System.out.println(rowNumbers);
        System.out.println(totalEntries);
    }
    private int extractTotalEntries(String entriesText) {
        // Extract the total number of entries from the format "Showing X to Y of Z entries"
        String[] parts = entriesText.split(" ");
        return Integer.parseInt(parts[parts.length - 2]);
    }
}
