package pageObjects;

import abstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class TShirtCategoryPage extends AbstractComponent {
    WebDriver driver;
    public TShirtCategoryPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".title")
    WebElement tShirtCatPageTitle;

    public String getTShirtCatPageTitle()
    {
        return tShirtCatPageTitle.getText();
    }
}
