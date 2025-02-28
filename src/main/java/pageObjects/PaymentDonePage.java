package pageObjects;

import abstractComponents.*;
import org.openqa.selenium.*;

public class PaymentDonePage extends AbstractComponent {
    WebDriver driver;
    public PaymentDonePage(WebDriver driver) {
        super(driver);
        this.driver=driver;

    }
}
