package pageObjects;

import abstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class DeleteAccount extends AbstractComponent {

    WebDriver driver;
    public DeleteAccount( WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    //driver.findElement(By.xpath("//div/h2/b")
    @FindBy(xpath = "//div/h2/b")
    WebElement accDeletionTxt;

    @FindBy(css = ".btn-primary")
    WebElement continueBtn;

    public String getAccDeletionTxt()
    {
        return accDeletionTxt.getText();
    }

    public void clickContinue()
    {
        continueBtn.click();
    }

}
