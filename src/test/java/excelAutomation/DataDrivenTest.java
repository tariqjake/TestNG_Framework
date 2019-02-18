package excelAutomation;

import com.weborders.utilities.BrowserUtils;
import com.weborders.utilities.ConfigurationReader;
import com.weborders.utilities.Driver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenTest {

    @Test
    public void employeeFormTest(){
        Driver.getDriver().get(ConfigurationReader.getProperty("urlxl"));
        EmployeesPage  ep = new EmployeesPage();


        ep.firstName.sendKeys("Mark");
        ep.lastName.sendKeys("Smith");
        ep.role.sendKeys("Student");
        ep.chooseGender(EmployeesPage.Gender.MALE);
        ep.email.sendKeys("markSmith@hotmail.com");
        ep.annaulSalary.sendKeys("125000");
        ep.selectEducation(EmployeesPage.Education.HIGH_SCHOOL);
        ep.chooseCertification(EmployeesPage.Certification.AWS,EmployeesPage.Certification.Java_OCA, EmployeesPage.Certification.Scrum_Master);
        ep.additionalSkillsTxt.sendKeys("SQL, BDD, Cucumber, Selenium");
        ep.submit.click();


        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),5);
        wait.until(ExpectedConditions.urlContains("thankyou"));
        wait.until(ExpectedConditions.visibilityOf())

        Assert.assertTrue(ep.applicationSubmitted.isDisplayed());



    }
}