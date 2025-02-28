package pageObjects;

import abstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class AccountCreated extends AbstractComponent {

    WebDriver driver;

    public AccountCreated(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    //      driver.findElement(By.xpath("//div/h2/b")
    @FindBy(xpath = "//div/h2/b")
    WebElement accCreationTxt;

    //Click 'Continue' button
    //        driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();
    @FindBy(xpath = "//a[contains(text(),'Continue')]")
    WebElement continueBtn;

    public String getAccCreationTxt()
    {
        return accCreationTxt.getText();
    }

    public void clickContinue()
    {
        continueBtn.click();
    }
}
