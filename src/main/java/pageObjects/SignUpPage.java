package pageObjects;

import abstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class SignUpPage extends AbstractComponent {

    WebDriver driver;

    public SignUpPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='login-form']/h2/b")
    WebElement accInfoText;

    @FindBy(xpath = "//input[@value='Mrs']")
    WebElement mrsRadioBtn;

    @FindBy(xpath = "//input[@value='Mr']")
    WebElement mrRadioBtn;

    @FindBy(id = "name")
    WebElement signUpName;

    @FindBy(id = "password")
    WebElement passwordBox;

    @FindBy(id = "days")
    WebElement days;

    @FindBy(id = "months")
    WebElement months;
    //    WebElement years = driver.findElement(By.id("years"));
    @FindBy(id = "years")
    WebElement years;

    // driver.findElement(By.cssSelector("#newsletter")).click();
    @FindBy(css = "#newsletter")
    WebElement newsletterCheckBox;

    //Select checkbox 'Receive special offers from our partners!'
    //   driver.findElement(By.xpath("//input[@id='optin']")).click();
    @FindBy(xpath = "//input[@id='optin']")
    WebElement partnerCheckbox;

    //driver.findElement(By.cssSelector("#first_name")).sendKeys("Scott");
    @FindBy(css = "#first_name")
    WebElement firstNameBox;

    //    driver.findElement(By.cssSelector("#last_name")).sendKeys("Smith");
    @FindBy(css = "#last_name")
    WebElement lastNameBox;

    // driver.findElement(By.xpath("//*[@data-qa='company']")).sendKeys("IFS");
    @FindBy(xpath = "//*[@data-qa='company']")
    WebElement companyField;

    // driver.findElement(By.id("address1")).sendKeys("No:365");
    @FindBy(id = "address1")
    WebElement address1Filed;

    //driver.findElement(By.id("address2")).sendKeys("Colombo");
    @FindBy(id = "address2")
    WebElement address2Field;

   @FindBy(xpath ="//*[@id='country']")
   WebElement countryList;

   @FindBy(id="state")
   WebElement stateField;

    @FindBy(id="city")
    WebElement cityField;

    @FindBy(id="zipcode")
    WebElement zipcodeField;

    @FindBy(id="mobile_number")
    WebElement mobileNoField;

    @FindBy(xpath = "//button[text()='Create Account']")
    WebElement createAccBtn;

    public WebElement getAccInfoTxt() {
        return accInfoText;
    }

    public void selectTitle(String title) {
        if(title.equalsIgnoreCase("Mr"))
        {mrRadioBtn.click();}
        else{mrsRadioBtn.click();}
    }

    public void clearAndEnterName(String userName) {
        signUpName.clear();
        signUpName.sendKeys(userName);
    }

    public void enterPassword(String password) {
        passwordBox.sendKeys(password);
    }

    public void enterDOB(String date, String month, String year) {
        dropdownHandles(days).selectByVisibleText(date);
        dropdownHandles(months).selectByVisibleText(month);
        dropdownHandles(years).selectByVisibleText(year);
    }

    public void selectNewsletterCheckbox() {
        newsletterCheckBox.click();
    }

    public void selectPartnerCheckbox() {
        partnerCheckbox.click();
    }

    public void enterAddressInfo(String firstName, String lastName, String company, String address1,
                                 String address2,String country,String state,String city,String zipcode,String mobile) {
        firstNameBox.sendKeys(firstName);
        lastNameBox.sendKeys(lastName);
       // scrollWindow();
        companyField.sendKeys(company);
        address1Filed.sendKeys(address1);
        address2Field.sendKeys(address2);
        dropdownHandles(countryList).selectByValue(country);
        stateField.sendKeys(state);
        cityField.sendKeys(city);
        zipcodeField.sendKeys(zipcode);
        mobileNoField.sendKeys(mobile);

    }

    public AccountCreated clickCreateAccBtn()
    {
        createAccBtn.click();
        AccountCreated accCreated =  new AccountCreated(driver);
        return accCreated;
    }

    public AccountCreated createNewAcc(String title,String password,String date,String month,String year,String firstName
    ,String lastName,String company,String address1,String adderss2,String county, String state,String city
    ,String zipcode,String mobile)
    {
        selectTitle(title);
     enterPassword(password);
      enterDOB(date, month, year);
        enterAddressInfo(firstName,lastName,company,address1,adderss2,county,state,city,zipcode, mobile);
        AccountCreated accCreated =clickCreateAccBtn();
        return accCreated;
    }

}
