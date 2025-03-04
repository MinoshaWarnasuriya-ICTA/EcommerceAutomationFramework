package abstractComponents;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import pageObjects.*;

import java.time.*;

public class AbstractComponent {
WebDriver driver;
    public AbstractComponent(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    //driver.findElement(By.xpath("//li[5]/a")).click();
    @FindBy(xpath = "//li[5]/a")
    WebElement deleteBtn;

    @FindBy(xpath = "//a[contains(text(),'Contact us')]")
    WebElement contactUsBtn;

    @FindBy(xpath = "//a[contains(text(),'Test Cases')]")
    WebElement testCasesBtn;

    @FindBy(xpath = "//a[contains(text(),'Products')]")
    WebElement productsHeaderIcon;

    //driver.findElement(By.xpath("//*[text()=' Signup / Login']"))
    @FindBy(xpath="//*[text()=' Signup / Login']")
    WebElement signUpBtn;

    @FindBy(xpath = "//*[contains(text(), 'Logged in as ')]")
    WebElement loggedInUserTxt;

   @FindBy(xpath = "//*[text()=' Logout']")
   WebElement logoutBtn;

   @FindBy(xpath = "//*[contains(text(),'Cart')]")
   WebElement cartHeaderBtn;

    @FindBy(css = "[class='single-widget'] h2")
    WebElement subscriptionText;

    @FindBy(id = "susbscribe_email")
    WebElement subscriptionMailBox;

    @FindBy(id = "subscribe")
    WebElement subscriptionArrowBtn;

    @FindBy(id = "success-subscribe")
    WebElement successSubscribeMsge;

    @FindBy(css = "li:last-child a")
    WebElement welcomeMsge;



    public LoginPage goToLoginPage()
    {
        // Click on 'Signup / Login' button
        signUpBtn.click();
        LoginPage loginPage = new LoginPage(driver);
        return loginPage;
    }

    public Select dropdownHandles(WebElement input)
    {
        Select dropdown = new Select(input);
        return dropdown;
    }

    public DeleteAccount deleteAcc()
    {
        deleteBtn.click();
        DeleteAccount deleteAccount = new DeleteAccount(driver);
        return deleteAccount;
    }

    public ContactUs clickContactUs()
    {
        contactUsBtn.click();
        ContactUs contactUs = new ContactUs(driver);
        return contactUs;
    }

    public void acceptAlert()
    {
        driver.switchTo().alert().accept();
    }

    public void clickTestCases()
    {
      testCasesBtn.click();
    }

    public Products goToAllProducts()
    {
        productsHeaderIcon.click();
        Products products = new Products(driver);
      return products;
    }

    public String getLoggedInUserTxt()
    {
       String actualLoggedInUserText = loggedInUserTxt.getText();
       return actualLoggedInUserText;
    }

    public void userLogout()
    {
        logoutBtn.click();
    }

    public CartPage goToCartPage()
    {
        cartHeaderBtn.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public String getSubscriptionTxt()
    {
        return subscriptionText.getText();
    }
    public String getWelcomeTxt()
    {
        String welcomeTxt =  welcomeMsge.getText();
        return welcomeTxt;
    }


    public void subscribe(String mailAddress)
    {
        subscriptionMailBox.sendKeys(mailAddress);
        subscriptionArrowBtn.click();
    }
    public String getSuccessSubscribeMsgeText()
    {
        return successSubscribeMsge.getText();
    }

    public void scrollWindow(int x)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+ x+")");
    }

    public void waitForLocatorToAppear(By findby)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
    }

    public void waitForVisibilityOfElement(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void mouseHover(WebDriver driver,WebElement element)
    {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();

    }

    public void scrollToElement(WebElement element)
    {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView();",element);
    }

}
