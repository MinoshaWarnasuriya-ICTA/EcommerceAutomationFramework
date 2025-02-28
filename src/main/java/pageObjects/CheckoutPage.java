package pageObjects;

import abstractComponents.*;
import io.opentelemetry.sdk.trace.data.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import java.util.*;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#address_invoice li:nth-child(2)")
    WebElement fullNameBox;

    @FindBy(css = "#address_invoice li:nth-child(3)")
    WebElement companyName;

    @FindBy(css = "#address_invoice li:nth-child(4)")
    WebElement addressLine1;

    @FindBy(css = "#address_invoice li:nth-child(5)")
    WebElement addressLine2;

    @FindBy(css = "#address_invoice li:nth-child(6)")
    WebElement cityStateZipcode;

    @FindBy(css = "#address_invoice li:nth-child(7)")
    WebElement countryName;

    @FindBy(css = "#address_invoice li:nth-child(8)")
    WebElement mobileNo;

    @FindBy(css = ".cart_description h4 a")
    List<WebElement> checkoutProdList;

    @FindBy(css = "[name='message']")
    WebElement msgeBox;

    @FindBy(css = ".check_out")
    WebElement placeOrderBtn;

    public boolean verifyAddressDetails(String title, String firstName, String lastName,
                                        String company, String address1, String address2, String city, String state, String zipcode,
                                        String country, String mobile) {
        if (fullNameBox.getText().equalsIgnoreCase(title + "." + " " + firstName + " " + lastName) &&
                companyName.getText().equalsIgnoreCase(company) &&
                addressLine1.getText().equalsIgnoreCase(address1) &&
                addressLine2.getText().equalsIgnoreCase(address2) &&
                cityStateZipcode.getText().equalsIgnoreCase(city + " " + state + " " + zipcode) &&
                countryName.getText().equalsIgnoreCase(country) &&
                mobileNo.getText().equalsIgnoreCase(mobile)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean verifyCheckoutProdList(String products) {
        List<String> productsList = Arrays.asList(products);
        for (int i = 0; i < productsList.size(); i++) {
            for (int j = i; j < checkoutProdList.size(); j++) {
                if (checkoutProdList.get(j).getText().equalsIgnoreCase(productsList.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public void enterMsge(String message)
    {
        msgeBox.sendKeys(message);
    }

    public PaymentPage clickPlaceOrderBtn()
    {
        placeOrderBtn.click();
        PaymentPage paymentPage = new PaymentPage(driver);
        return paymentPage;
    }

}
