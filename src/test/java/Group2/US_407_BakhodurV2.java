package Group2;

import Utilities.DriverClassV2;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class US_407_BakhodurV2 extends DriverClassV2 {
    @Test(dependsOnMethods = {"Group2.US_404_BakhodurV2.US_402_Login", "Group2.US_404_BakhodurV2.US_404_Patient_Registration"}, groups = {"Smoke", "PatientManagement"})
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
