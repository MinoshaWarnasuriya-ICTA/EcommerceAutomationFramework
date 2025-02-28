package pageObjects;

import abstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class PaymentPage extends AbstractComponent {
    WebDriver driver;

    public PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[name='name_on_card']")
    WebElement nameOnCard;

    @FindBy(css = "[name='card_number']")
    WebElement cardNumber;

    @FindBy(xpath = "//*[@name='cvc']")
    WebElement cvcNumber;

    @FindBy(xpath = "//*[@name='expiry_month']")
    WebElement expireMonth;

    @FindBy(xpath = "//*[@name='expiry_year']")
    WebElement expiryYear;

    @FindBy(id = "submit")
    WebElement placeOrderBtn;

    @FindBy(css = "#success_message div")
    WebElement successMsge;

    public void fillPaymentDetails(String name_on_card, String card_no, String cvc, String expire_month, String expiry_year) {
        scrollWindow(250);
        nameOnCard.sendKeys(name_on_card);
        cardNumber.sendKeys(card_no);
        cvcNumber.sendKeys(cvc);
        expireMonth.sendKeys(expire_month);
        expiryYear.sendKeys(expiry_year);

    }

    public PaymentDonePage clickPlaceOrderBtn() {
        placeOrderBtn.click();
        PaymentDonePage paymentDonePage = new PaymentDonePage(driver);
        return paymentDonePage;
    }

    public String getSuccessMsge() {

        waitForVisibilityOfElement(successMsge);
      return  successMsge.getText();

    }


}
