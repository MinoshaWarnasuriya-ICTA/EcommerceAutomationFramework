package pageObjects;

import abstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class HomePage extends AbstractComponent {

    WebDriver driver;

    public HomePage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    //driver.findElement(By.cssSelector(".logo a img")
    @FindBy(css=".logo a img")
    WebElement logo;



   // String name = driver.findElement(By.cssSelector("li:last-child a b")).getText();
    @FindBy(css = "li:last-child a b")
    WebElement visibleName;

    //driver.findElement(By.cssSelector("li:last-child a")).getText()
    @FindBy(css = "li:last-child a")
    WebElement welcomeMsge;

    @FindBy(xpath = "(//h4[@class='panel-title'])[1]/a")
    WebElement womenCategory;

    @FindBy(xpath = "(//h4[@class='panel-title'])[2]/a")
    WebElement menCategory;

    @FindBy(css = "#Women div ul li:nth-child(3) a")
    WebElement sareeCategory;

    @FindBy(xpath = "//*[contains(text(),'Tshirts')]")
    WebElement tShirtCategory;

    @FindBy(xpath = "//h2[contains(text(),'Category')]")
    WebElement categoryTitle;


    public void goTo()
    {
        //Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");
    }

    public WebElement getLogo()
    {
        return logo;
    }



    public String getVisibleName()
    {
        String name = visibleName.getText();
        return name  ;
    }

    public String getWelcomeTxt()
    {
       String welcomeTxt =  welcomeMsge.getText();
       return welcomeTxt;
    }

public void clickWomenCategory()
{
    womenCategory.click();
}

public SareeCategoryPage selectSareeCategory()
{
     sareeCategory.click();
    SareeCategoryPage sareePage = new SareeCategoryPage(driver);
    return sareePage;
}

public void clickMenCategory()
{
    menCategory.click();
}

public TShirtCategoryPage selectTshirtCategory()
{
    tShirtCategory.click();
    TShirtCategoryPage tShirtPage = new TShirtCategoryPage(driver);
    return tShirtPage;
}

public String getCategoryTitle()
{
return categoryTitle.getText();
}


   }
