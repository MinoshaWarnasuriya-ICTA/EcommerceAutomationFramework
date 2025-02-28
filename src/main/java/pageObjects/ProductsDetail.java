package pageObjects;

import abstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class ProductsDetail extends AbstractComponent {
    WebDriver driver;

    public ProductsDetail(WebDriver driver) {
        super(driver);
        this.driver = this.driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='product-information']/h2")
    WebElement prodName;

    @FindBy(xpath = "(//div[@class='product-information']/p)[1]")
    WebElement prodCategory;

    @FindBy(xpath = "//div[@class='product-information']/span/span")
    WebElement price;

    @FindBy(xpath = "(//div[@class='product-information']/p)[2]")
    WebElement availability;

    @FindBy(xpath = "(//div[@class='product-information']/p)[3]")
    WebElement condition;

    @FindBy(xpath = "(//div[@class='product-information']/p)[4]")
    WebElement brand;
    @FindBy(id = "quantity")
    WebElement quantityInputBox;

    @FindBy(css = ".cart")
    WebElement addToCartBtn;

    @FindBy(css = ".btn-success")
    WebElement continueShoppingBtn;

    public void setQuantity(String quantity) {
        quantityInputBox.clear();
        quantityInputBox.sendKeys(quantity);
    }

    public void clickAddToCart() {
        addToCartBtn.click();
        waitForVisibilityOfElement(continueShoppingBtn);
        continueShoppingBtn.click();
    }


    public WebElement getProdName() {
        return prodName;
    }

    public WebElement getProdCategory() {
        return prodCategory;
    }

    public WebElement getProdPrice() {
        return price;
    }

    public WebElement getAvailability() {
        return availability;
    }

    public WebElement getCondition() {
        return condition;
    }

    public WebElement getBrand() {
        return brand;
    }
}
