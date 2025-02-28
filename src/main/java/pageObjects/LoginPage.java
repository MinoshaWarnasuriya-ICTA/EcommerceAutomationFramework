package pageObjects;

import abstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class LoginPage extends AbstractComponent {

    WebDriver driver;
    public LoginPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    //driver.findElement(By.cssSelector("div[class='signup-form'] h2")
    @FindBy(css="div[class='signup-form'] h2")
    WebElement signupTxt;

    // driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Scott");
    @FindBy(xpath="//input[@name='name']")
    WebElement nameBox;

    //driver.findElement(By.cssSelector("input[data-qa='signup-email']")).sendKeys("scott@gmail.com");
    @FindBy(css="input[data-qa='signup-email']")
    WebElement emailBox;

    // driver.findElement(By.xpath("//button[text()='Signup']")).click();
    @FindBy(xpath="//button[text()='Signup']")
    WebElement signUpBtn;

    @FindBy(xpath = "//h2[text()='Login to your account']")
    WebElement loginToYourAccMsge;

    @FindBy(xpath = "(//input[@name='email'])[1]")
    WebElement loginEmailInput;

  @FindBy(xpath = "//input[@data-qa='login-password']")
  WebElement loginPasswordInput;

 @FindBy(xpath = "//button[@data-qa='login-button']")
 WebElement loginBtn;

@FindBy(xpath = "//form[@action='/login']/p")
WebElement invalidLoginErrorMsge;

@FindBy(xpath = "//form[@action='/signup']/p")
WebElement invalidSignupMsge;

    public WebElement getSignUpTxt()
    {
        return signupTxt;
    }

    public void enterName(String userName)
    {
        nameBox.sendKeys(userName);
    }

    public void enterSignupEmail(String email)
    {
        emailBox.sendKeys(email);
    }

    public SignUpPage clickSignUp()
    {
        signUpBtn.click();
        SignUpPage signUpPage=new SignUpPage(driver);
        return signUpPage;
    }

    public String getLoginToAccMsgeTxt()
    {
       String LoginToAccMsgeTxt=  loginToYourAccMsge.getText();
       return LoginToAccMsgeTxt;
    }

public void login(Object email,Object password)
{
    loginEmailInput.sendKeys((CharSequence) email);
    loginPasswordInput.sendKeys((CharSequence) password);
    loginBtn.click();
}

public String getInvalidLoginErrorMsgeText()
{
    String errorMsge = invalidLoginErrorMsge.getText();
    return errorMsge;
}

public String getInvalidSignupErrorMsgeText()
{
   return invalidSignupMsge.getText();
}

public SignUpPage fillNewUserSignUp(String name, String email)
{
    enterName(name);
    enterSignupEmail(email);
    SignUpPage signUpPage = clickSignUp();

    return signUpPage;
}
}
