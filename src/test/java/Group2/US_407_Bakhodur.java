package Group2;

import Utilities.DriverClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class US_407_Bakhodur extends DriverClass {
    
    @Test()
    void US_402_Login() {
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
        driver.findElement(By.linkText("Demo")).click();
        driver.findElement(By.xpath("//span[text()=\"Explore OpenMRS 2\"]")).click();
        driver.findElement(By.xpath("//span[text()=\"Enter the OpenMRS 2 Demo\"]")).click();
    }
    
    @Test(dependsOnMethods = "US_402_Login")
    @Parameters({"username", "password", "givenName", "familyName", "birthdateDay", "birthdateMonth",
            "birthdateYear", "address", "phoneNumber", "relationship"})
    void US_404_Patient_Registration(@Optional("admin") String username,
                                     @Optional("Admin123") String password,
                                     @Optional("Group2_GName") String givenName,
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
//        driver.findElement(By.id("username")).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        List<WebElement> locationList = driver.findElements(By.cssSelector("ul[id=\"sessionLocation\"]>li"));
        int randomIndex = (int) (Math.random() * locationList.size());
        locationList.get(randomIndex).click();
        driver.findElement(By.id("loginButton")).click();
        
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
    
    @Test(dependsOnMethods = {"US_402_Login", "US_404_Patient_Registration"}, groups = {"Smoke", "PatientManagement"})
    @Parameters({"searchName", "deleteReason"})
    void US_407_Patient_Deletion(@Optional("Group2_GName") String searchName,
                                 @Optional("_Privacy") String deleteReason) {
        /*
        ## Acceptance Criteria (AC_07)
        1. The doctor, logged in as [admin] on the application homepage.
        2. The doctor clicks on the [Find Patient Record] button.
        3. The doctor enters the name or ID of the patient they want to find in the search box.
        4. The doctor should be able to click on the found patient in the search result.
        5. Clicking on the patient, the doctor should access a page where they can perform actions related to the patient.
        6. On this page, the doctor finds and clicks the [Delete Patient] link under the "General Actions" tab.
        7. The doctor confirms the deletion by entering a reason in the confirmation box.
        8. The doctor successfully deletes the patient.
        9. The patient's records are removed from the system.
        */
        driver.get("https://demo.openmrs.org/openmrs/referenceapplication/home.page");
        driver.findElement(By.xpath("//a[normalize-space()=\"Find Patient Record\"] ")).click();
        driver.findElement(By.id("patient-search")).sendKeys(searchName);
        driver.findElement(By.xpath("//td[contains(text(),\"" + searchName + "\")]")).click();
        driver.findElement(By.xpath("//div[contains(text(),\"Delete Patient\")]")).click();
        driver.findElement(By.id("delete-reason")).sendKeys(deleteReason);
        driver.findElement(By.xpath("(//button[@class=\"confirm right\"])[6]")).click();
    }
}
