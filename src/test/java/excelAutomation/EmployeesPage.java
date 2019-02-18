package excelAutomation;

import com.weborders.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;



public class EmployeesPage {
    public enum Gender {
        MALE, FEMALE;
    }

    public enum Education{
        HIGH_SCHOOL, UNDER_GRADUATE, POST_GRADUATE,BOOT_CAMP;
    }

    public enum Certification {
        Java_OCA, AWS, Scrum_Master;
    }

    public EmployeesPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(name = "Name_First")
    public WebElement firstName;

    @FindBy(name = "Name_Last")
    public WebElement lastName;

    @FindBy(name = "SingleLine")
    public WebElement role;

    @FindBy(name = "Email")
    public WebElement email;

    @FindBy(id = "Radio_1")
    public WebElement male;

    @FindBy(id = "Radio_2")
    public WebElement female;

    @FindBy(name = "Number")
    public WebElement annaulSalary;

    @FindBy(name = "Formula")
    public WebElement monthly;

    @FindBy(name = "Formula1")
    public WebElement weekly;

    @FindBy(name = "Formula2")
    public WebElement hourly;

    @FindBy(name = "Dropdown")
    public WebElement educationDropDown;

    @FindBy(id = "Checkbox_1")
    public WebElement javaOCA;

    @FindBy(id = "Checkbox_2")
    public WebElement AWS;

    @FindBy(id = "Checkbox_3")
    public WebElement scrumMaster;

    @FindBy(name = "MultiLine")
    public WebElement additionalSkillsTxt;

    @FindBy(tagName = "button")
    public WebElement submit;



    public void chooseGender(String gender) {
        if (gender.equalsIgnoreCase("male") && !male.isSelected()) {
            male.click();
        } else if (gender.equalsIgnoreCase("female") && !female.isSelected()) {
            female.click();
        }
    }

    public void selectEducation(String education){
        Select select = new Select(educationDropDown);
        switch (education){
            case "High School":
                select.selectByVisibleText("High School");
                break;
            case "Under Graduate":
                select.selectByVisibleText("Under Graduate");
                break;
            case "Post Graduate":
                select.selectByVisibleText("Post Graduate");
                break;
            case "Boot Camp":
                select.selectByVisibleText("Boot Camp");
                break;
        }
    }

    public void chooseCertification(String certification){

            switch (certification){
                case "AWS":
                    AWS.click();
                    break;
                case "Java OCA":
                    javaOCA.click();
                    break;
                case "Scrum Master":
                    scrumMaster.click();
                    break;


        }
    }


}
