package pageObjects;

import abstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class ContactUs extends AbstractComponent {
    WebDriver driver;
    public ContactUs(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@class='contact-form']/h2")
    WebElement getInTouchText;

    @FindBy(xpath = "//input[@name='name']")
    WebElement nameBox;

    @FindBy(xpath = "//input[@name='email']")
    WebElement emailBox;

    @FindBy(css = "input[name='subject']")
    WebElement subjectBox;

    @FindBy(id="message")
    WebElement msgeBox;

    @FindBy(css = "input[type='file']")
    WebElement fileUploadBox;

    @FindBy(xpath = "//input[@name='submit']")
    WebElement submitBtn;

    @FindBy(xpath = "(//div[@class='contact-form']/div)[2]")
    WebElement successMsge;

    @FindBy(xpath = "//span[contains(text(),'Home')]")
    WebElement homeBtn;
    public WebElement getGetInTouchText()
    {
        return getInTouchText;
    }

    public void sendName(String name)
    {
        nameBox.sendKeys(name);
    }

    public void sendEmail(String email)
    {
        emailBox.sendKeys(email);
    }

    public void enterSubject(String subject)
    {
        subjectBox.sendKeys(subject);
    }

    public void enterMsge(String message)
    {
        msgeBox.sendKeys(message);
    }

    public void uploadFile(String filePath) {
        fileUploadBox.sendKeys(filePath);
    }

    public void clickSubmit()
    {
        submitBtn.click();
    }

    public String getSuccessMsgeText()
    {
       String actualText = successMsge.getText();
       return actualText;
    }

    public void clickHomeBtn()
    {
        homeBtn.click();
    }
}
