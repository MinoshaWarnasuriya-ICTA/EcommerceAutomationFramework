package pageObjects;

import abstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class SareeCategoryPage extends AbstractComponent {
    WebDriver driver;
    public SareeCategoryPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

@FindBy(css = ".breadcrumb li:nth-child(2)")
    WebElement sareePageCategoryName;

    public String getSareePageCatName()
    {
      return  sareePageCategoryName.getText();
    }
}
