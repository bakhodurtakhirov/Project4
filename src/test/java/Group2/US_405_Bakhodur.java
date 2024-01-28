package Group2;

import Utilities.DriverClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.List;

public class US_405_Bakhodur extends DriverClass {
    
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
    
    @Test(dependsOnMethods = "US_402_Login", groups = "Smoke")
    @Parameters({"username", "password"})
    void US_404_Patient_Registration(@Optional("admin") String username,
                                     @Optional("Admin123") String password) {
        /*
        ## Acceptance Criteria (AC_05)
        1. The user is on the Login page.
        2. The user randomly clicks on the Location button.
        3. The user successfully logs in (Depends on [US_402].
        4. When hovering over the profile icon, the user should see the [My Account] link, and it should be clickable.
        5. Upon clicking the [My Account] link, the user should be able to see and click on the "Change Password" and "My Languages" buttons.
        */
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        List<WebElement> locationList = driver.findElements(By.cssSelector("ul[id=\"sessionLocation\"]>li"));
        int randomIndex = (int) (Math.random() * locationList.size());
        locationList.get(randomIndex).click();
        driver.findElement(By.id("loginButton")).click();
        
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector("li[class=\"nav-item identifier\"]"))).perform();
        
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href=\"/openmrs/adminui/myaccount/myAccount.page\"]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href=\"/openmrs/adminui/myaccount/changePassword.page\"]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href=\"/openmrs/adminui/myaccount/changeDefaults.page\"]")));
    }
}
