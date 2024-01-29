package Group2;

import Utilities.DriverClassV2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class US_404_BakhodurV2 extends DriverClassV2 {
    @Test()
    @Parameters({"username", "password"})
    void US_402_Login(@Optional("admin") String username,
                      @Optional("Admin123") String password) {
        /*
        ## Acceptance Criteria (AC_02)
        1. The user navigates to the home page.
        2. Clicks on the [*DEMO*] button.
        3. Selects [*Explore OpenMRS 2*].
        4. Clicks on [*Enter the OpenMRS 2 Demo*].
        5. Navigates to the login page.
        6. Enters the valid username and password from Customer Data.
        7. Clicks on the desired [Location] button for login.
        8. After entering the valid username and password, the user can log in to their registered user account by clicking the "**Login**" button.
        */
        driver.get("https://openmrs.org/");
        driver.findElement(By.linkText("Demo")).click();
        driver.findElement(By.xpath("//span[text()=\"Explore OpenMRS 2\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=\"Enter the OpenMRS 2 Demo\"]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        List<WebElement> locationList = driver.findElements(By.cssSelector("ul[id=\"sessionLocation\"]>li"));
        int randomIndex = (int) (Math.random() * locationList.size());
        locationList.get(randomIndex).click();
        driver.findElement(By.id("loginButton")).click();
    }
    
    @Test(dependsOnMethods = "US_402_Login", groups = "Regression")
    @Parameters({"givenName", "familyName", "birthdateDay", "birthdateMonth",
            "birthdateYear", "address", "phoneNumber", "relationship"})
    void US_404_Patient_Registration(@Optional("Group2_GName") String givenName,
                                     @Optional("Group2_FName") String familyName,
                                     @Optional("1") String birthdateDay,
                                     @Optional("January") String birthdateMonth,
                                     @Optional("1990") String birthdateYear,
                                     @Optional("My address") String address,
                                     @Optional("1111111111") String phoneNumber,
                                     @Optional("Doctor") String relationship) {
        /*
        ## Acceptance Criteria (AC_04)
        1. The user is on the Login page.
        2. The user randomly clicks on the Location button.
        3. The user successfully logs in (Depends on [US_402].
        4. The user clicks on the [Register a patient] button.
        5. The user enters the data provided by Customer Data (Demographics / Contact Info / Relationships).
        6. The user clicks the Confirm button and is redirected to the patient page.
        7. The user should be able to view and verify the name and ID of the registered patient on the patient's page.
        */
        driver.findElement(By.linkText("Register a patient")).click();
        driver.findElement(By.cssSelector("input[name=\"givenName\"]")).sendKeys(givenName);
        driver.findElement(By.cssSelector("input[name=\"familyName\"]")).sendKeys(familyName);
        driver.findElement(By.id("next-button")).click();
        
        driver.findElement(By.cssSelector("option[value=\"M\"]")).click();
        driver.findElement(By.id("next-button")).click();
        
        driver.findElement(By.id("birthdateDay-field")).sendKeys(birthdateDay);
        new Select(driver.findElement(By.id("birthdateMonth-field"))).selectByVisibleText(birthdateMonth);
        driver.findElement(By.id("birthdateYear-field")).sendKeys(birthdateYear);
        driver.findElement(By.id("next-button")).click();
        
        driver.findElement(By.id("address1")).sendKeys(address);
        driver.findElement(By.id("next-button")).click();
        
        driver.findElement(By.cssSelector("input[name=\"phoneNumber\"]")).sendKeys(phoneNumber);
        driver.findElement(By.id("next-button")).click();
        
        new Select(driver.findElement(By.id("relationship_type"))).selectByVisibleText(relationship);
        driver.findElement(By.id("next-button")).click();
        
        driver.findElement(By.id("submit")).click();
        
        String givenNameActual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[class=\"PersonName-givenName\"]"))).getText();
        String familyNameActual = driver.findElement(By.cssSelector("span[class=\"PersonName-familyName\"]")).getText();
        Assert.assertEquals(givenNameActual, givenName);
        Assert.assertEquals(familyNameActual, familyName);
        
        System.out.println("Patient ID: " + driver.findElement(By.cssSelector("div[class=\"float-sm-right\"]>span")).getText());
    }
}
