package excelAutomation;

import com.weborders.utilities.BrowserUtils;
import com.weborders.utilities.ConfigurationReader;
import com.weborders.utilities.Driver;
import com.weborders.utilities.TestBase;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenTest extends TestBase {

    @Test
    public void employeeFormTest() throws InterruptedException {
        Driver.getDriver().get(ConfigurationReader.getProperty("urlxl"));
        EmployeesPage  ep = new EmployeesPage();


        ep.firstName.sendKeys("Mark");
        ep.lastName.sendKeys("Smith");
        ep.role.sendKeys("Student");
        ep.chooseGender("Male");
        ep.email.sendKeys("markSmith@hotmail.com");
        ep.annaulSalary.sendKeys("125000");
        ep.selectEducation("High School");
        ep.chooseCertification("AWS");
        ep.additionalSkillsTxt.sendKeys("SQL, BDD, Cucumber, Selenium");
        ep.submit.click();


//        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),5);
//        wait.until(ExpectedConditions.urlContains("thankyou"));
//        BrowserUtils.waitForPageToLoad(5);
        ConfimationPage cp = new ConfimationPage();
        Assert.assertEquals(cp.getFullName(), "Mark Smith");



    }
}
