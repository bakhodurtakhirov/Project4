package Group2;

import Utilities.DriverClassV2;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class US_405_BakhodurV2 extends DriverClassV2 {
    @Test(dependsOnMethods = "Group2.US_404_BakhodurV2.US_402_Login", groups = "Smoke")
    @Parameters({"username", "password"})
    void US_405_My_account(@Optional("admin") String username,
                           @Optional("Admin123") String password) {
        /*
        ## Acceptance Criteria (AC_05)
        1. The user is on the Login page.
        2. The user randomly clicks on the Location button.
        3. The user successfully logs in (Depends on [US_402].
        4. When hovering over the profile icon, the user should see the [My Account] link, and it should be clickable.
        5. Upon clicking the [My Account] link, the user should be able to see and click on the "Change Password" and "My Languages" buttons.
        */
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector("li[class=\"nav-item identifier\"]"))).perform();
        
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href=\"/openmrs/adminui/myaccount/myAccount.page\"]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href=\"/openmrs/adminui/myaccount/changePassword.page\"]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href=\"/openmrs/adminui/myaccount/changeDefaults.page\"]")));
    }
}
