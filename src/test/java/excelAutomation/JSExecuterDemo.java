package excelAutomation;

import com.weborders.utilities.BrowserUtils;
import com.weborders.utilities.ConfigurationReader;
import com.weborders.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

public class JSExecuterDemo extends TestBase {


    @Test
    public void test() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        jse.executeScript("alert('You are doing great!!')");
//        BrowserUtils.wait(4);
        driver.get("https://google.com");
         jse.executeScript("arguments[0].click();",driver.findElement(By.name("btnI")));

        BrowserUtils.wait(4);

    }

}
