package pageObjects;

import abstractComponents.*;
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
    WebElement billingFullNameBox;

    @FindBy(css = "#address_invoice li:nth-child(3)")
    WebElement billinCompanyName;

    @FindBy(css = "#address_invoice li:nth-child(4)")
    WebElement billingAddressLine1;

    @FindBy(css = "#address_invoice li:nth-child(5)")
    WebElement billingAddressLine2;

    @FindBy(css = "#address_invoice li:nth-child(6)")
    WebElement billingCityStateZipcode;

    @FindBy(css = "#address_invoice li:nth-child(7)")
    WebElement billingCountryName;

    @FindBy(css = "#address_invoice li:nth-child(8)")
    WebElement billingMobileNo;

    @FindBy(css = ".cart_description h4 a")
    List<WebElement> checkoutProdList;

    @FindBy(css = "[name='message']")
    WebElement msgeBox;

    @FindBy(css = ".check_out")
    WebElement placeOrderBtn;

    @FindBy(css = "#address_delivery li:nth-child(2)")
    WebElement deliveryFullName;

    @FindBy(css = "#address_delivery li:nth-child(3)")
    WebElement deliveryCompanyName;

    @FindBy(css = "#address_delivery li:nth-child(4)")
    WebElement deliveryAddress1;

    @FindBy(css = "#address_delivery li:nth-child(5)")
    WebElement deliveryAddress2;

    @FindBy(css = "#address_delivery li:nth-child(6)")
    WebElement  deliveryCityStateZipcode;

    @FindBy(css = "#address_delivery li:nth-child(7)")
    WebElement deliveryCountry;

    @FindBy(css = "#address_delivery li:nth-child(8)")
    WebElement deliveryMobile;





    public boolean verifyBillingAddressDetails(String title, String firstName, String lastName,
                                        String company, String address1, String address2, String city, String state, String zipcode,
                                        String country, String mobile) {
        if (billingFullNameBox.getText().equalsIgnoreCase(title + "." + " " + firstName + " " + lastName) &&
                billinCompanyName.getText().equalsIgnoreCase(company) &&
                billingAddressLine1.getText().equalsIgnoreCase(address1) &&
                billingAddressLine2.getText().equalsIgnoreCase(address2) &&
                billingCityStateZipcode.getText().equalsIgnoreCase(city + " " + state + " " + zipcode) &&
                billingCountryName.getText().equalsIgnoreCase(country) &&
                billingMobileNo.getText().equalsIgnoreCase(mobile)) {
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

    public boolean verifyDeliveryAddressDetails(String title, String firstName, String lastName,
                                                String company, String address1, String address2,
                                                String city, String state, String zipcode,
                                                String country, String mobile) {
        if (deliveryFullName.getText().equalsIgnoreCase(title + "." + " " + firstName + " " + lastName) &&
                deliveryCompanyName.getText().equalsIgnoreCase(company) &&
                deliveryAddress1.getText().equalsIgnoreCase(address1) &&
                deliveryAddress2.getText().equalsIgnoreCase(address2) &&
                deliveryCityStateZipcode.getText().equalsIgnoreCase(city + " " + state + " " + zipcode) &&
                deliveryCountry.getText().equalsIgnoreCase(country) &&
                deliveryMobile.getText().equalsIgnoreCase(mobile)) {
            return true;
        } else {
            return false;
        }


    }
}
