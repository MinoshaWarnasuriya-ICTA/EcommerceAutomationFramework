package pageObjects;

import abstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class TopsAndShirts extends AbstractComponent {

    WebDriver driver;
    public TopsAndShirts(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".active")
    WebElement pageTitle;

    public String getTitle(){
      return  pageTitle.getText();
    }
}
