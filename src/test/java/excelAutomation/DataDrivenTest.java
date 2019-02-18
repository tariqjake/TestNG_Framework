package excelAutomation;

import com.weborders.utilities.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class DataDrivenTest extends TestBase {
    private List<Map<String, String>> dateTable;

    @BeforeMethod
    public void setUp() {
        ExcelUtils eu = new ExcelUtils("src/test/resources/EmployeesTestData.xls", "data");
        dateTable = eu.getDataList();
    }


    @Test
    public void employeeFormTest() throws InterruptedException {
        Driver.getDriver().get(ConfigurationReader.getProperty("urlxl"));
        EmployeesPage ep = new EmployeesPage();

        for (Map<String, String> person : dateTable) {
            ep.firstName.sendKeys(person.get("first_name"));
            ep.lastName.sendKeys(person.get("last_name"));
            ep.role.sendKeys(person.get("Role"));
            ep.chooseGender(person.get("gender"));
            ep.email.sendKeys(person.get("email"));
            ep.annaulSalary.sendKeys("" + (int)(double)Double.valueOf(person.get("Salary")));
            ep.selectEducation(person.get("Education"));
            ep.chooseCertification(person.get("Certifications"));
            ep.additionalSkillsTxt.sendKeys(person.get("Technical Skills"));
            ep.submit.click();


//        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),5);
//        wait.until(ExpectedConditions.urlContains("thankyou"));
//        BrowserUtils.waitForPageToLoad(5);
            ConfimationPage cp = new ConfimationPage();
            Assert.assertEquals(cp.getFullName(), person.get("first_name")+ " "+person.get("last_name"));

            Driver.getDriver().get(ConfigurationReader.getProperty("urlxl"));
        }
    }
}
