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

    @FindBy(css = ".nav-tabs li a")
    WebElement writeReviewTitle;

    @FindBy(css = "#name")
    WebElement reviewNameBox;

    @FindBy(css = "#email")
    WebElement reviewEmailBox;

    @FindBy(xpath = "//*[@id='review']")
    WebElement reviewInputBox;

    @FindBy(id = "button-review")
    WebElement reviewSubmitBtn;

    @FindBy(css = "[class*='alert-success'] span")
    WebElement successAlertMsge;

    public void setQuantity(String quantity) {
        quantityInputBox.clear();
        quantityInputBox.sendKeys(quantity);
    }

    public void clickAddToCart() {
        addToCartBtn.click();
        waitForVisibilityOfElement(continueShoppingBtn);
        continueShoppingBtn.click();
    }


    public String getProdName() {
        String product = prodName.getText();
        return product;
    }

    public String  getProdCategory() {
       String category = prodCategory.getText().split(": ")[1];
       return category;
    }

    public String getProdPrice() {
        String priceText = price.getText().split(" ")[1];
        return priceText;
    }

    public String getAvailability() {
       String availabilityType = availability.getText().split(": ")[1];
       return availabilityType;
    }

    public String getCondition() {
      String conditionTxt = condition.getText().split(": ")[1];
      return conditionTxt;
    }

    public String getBrand() {
       String brandName =  brand.getText().split(": ")[1];
       return brandName;
    }

    public String getWriteReviewTitleText()
    {
       return writeReviewTitle.getText();
    }

    public void addReview(String name, String email, String review) {
        reviewNameBox.sendKeys(name);
        reviewEmailBox.sendKeys(email);
        reviewInputBox.sendKeys(review);
        reviewSubmitBtn.click();
    }

    public String getSuccessAlertMsgeText()
    {
        waitForVisibilityOfElement(successAlertMsge);
       return successAlertMsge.getText();
    }
}
