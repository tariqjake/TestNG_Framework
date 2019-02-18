package excelAutomation;

import com.weborders.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfimationPage {


    public ConfimationPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//div[contains(text(),'Dear')]")
    public WebElement dearMessage;


    public String getFullName(){
        String name =  dearMessage.getText();
        name = name.replace("Dear", "");
        name = name.replace(",", "");
        return name.trim();
    }

}
