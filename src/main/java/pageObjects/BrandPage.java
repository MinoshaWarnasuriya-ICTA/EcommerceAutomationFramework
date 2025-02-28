package pageObjects;

import abstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import java.util.*;

public class BrandPage extends AbstractComponent {
    WebDriver driver;
    public BrandPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

@FindBy(css = ".title")
    WebElement brandPageTitle;

    @FindBy(css = ".single-products")
    List<WebElement> brandProductsList;

    public String getBrandPageTitle()
    {
       return brandPageTitle.getText();
    }

    public List<WebElement> getBrandProdList()
    {
      return  brandProductsList;
    }

}
