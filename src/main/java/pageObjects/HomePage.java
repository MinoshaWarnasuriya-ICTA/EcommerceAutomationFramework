package pageObjects;

import abstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import java.util.*;

public class HomePage extends AbstractComponent {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //driver.findElement(By.cssSelector(".logo a img")
    @FindBy(css = ".logo a img")
    WebElement logo;


    // String name = driver.findElement(By.cssSelector("li:last-child a b")).getText();
    @FindBy(css = "li:last-child a b")
    WebElement visibleName;

    //driver.findElement(By.cssSelector("li:last-child a")).getText()
    @FindBy(css = "li:last-child a")
    WebElement welcomeMsge;

    @FindBy(xpath = "(//h4[@class='panel-title'])[1]/a")
    WebElement womenCategory;

    @FindBy(xpath = "(//h4[@class='panel-title'])[2]/a")
    WebElement menCategory;

    @FindBy(xpath = "(//h4[@class='panel-title'])[3]/a")
    WebElement kidsCategory;

    @FindBy(css = "#accordian")
    WebElement mainCategories;

    @FindBy(css = "#Women div ul li:nth-child(3) a")
    WebElement sareeCategory;

    @FindBy(xpath = "//*[contains(text(),'Tshirts')]")
    WebElement tShirtCategory;

    @FindBy(xpath = "//h2[contains(text(),'Category')]")
    WebElement categoryTitle;

    @FindBy(css = ".recommended_items")
    WebElement recommendedItemsArea;

    @FindBy(css = ".recommended_items .title")
    WebElement recommendedItemsText;

    @FindBy(css = "#recommended-item-carousel .product-image-wrapper")
    List<WebElement> recommendedProdList;

    @FindBy(css = ".modal-footer button")
    WebElement continueShoppingBtn;

    @FindBy(css = "#Women div ul li:nth-of-type(1) a")
    WebElement womenDress;

    @FindBy(css = "#Women div ul li:nth-of-type(2) a")
    WebElement womenTops;

    @FindBy(css = "#Men div ul li:nth-of-type(1) a")
    WebElement menTshirt;

    @FindBy(css = "#Men div ul li:nth-of-type(2) a")
    WebElement menJeans;

    @FindBy(css = "#Men div ul li:nth-of-type(2) a")
    WebElement kidCategory;

    @FindBy(css = "#Kids div ul li:nth-of-type(1) a")
    WebElement kidsDress;

    @FindBy(css = "#Kids div ul li:nth-of-type(2) a")
    WebElement kidsTopsAndShirts;

    @FindBy(linkText = "View Cart")
    WebElement viewCartLink;

    public @FindBy(xpath = "//div[@id='slider-carousel']/div/div[@class='item active']/div/h2")
    WebElement carouselText;

    By prodName = By.cssSelector(".productinfo p");

    By addToCartBtn = By.cssSelector(".productinfo a");


    public void goTo() {
        //Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");
    }

    public WebElement getLogo() {
        return logo;
    }


    public String getVisibleName() {
        String name = visibleName.getText();
        return name;
    }

    public String getWelcomeTxt() {
        String welcomeTxt = welcomeMsge.getText();
        return welcomeTxt;
    }

    public void clickWomenCategory() {
        scrollToElement(womenCategory);
        womenCategory.click();
    }

    public void viewMainCategory(String category) {
        scrollToElement(mainCategories);
        waitForVisibilityOfElement(mainCategories);
        if (category.equalsIgnoreCase("WOMEN")) {
            womenCategory.click();
        } else if (category.equalsIgnoreCase("MEN")) {
            menCategory.click();
        } else {
            kidsCategory.click();
        }

    }

public void selectWomenDress()
{
    waitForVisibilityOfElement(womenDress);
    womenDress.click();
}
public void selectWomenTops()
{
    waitForVisibilityOfElement(womenTops);
    womenTops.click();
}

public void selectKisdDress(){
        waitForVisibilityOfElement(kidsDress);
        kidsDress.click();
}

public void clickKidsTopsAndShirts(){
        waitForVisibilityOfElement(kidsTopsAndShirts);
        kidsTopsAndShirts.click();
}
public SareeCategoryPage selectSareeCategory() {
    sareeCategory.click();
    SareeCategoryPage sareePage = new SareeCategoryPage(driver);
    return sareePage;
}

public void clickMenCategory() {
    menCategory.click();
}

public void selectMenTShirt(){
        menTshirt.click();
}

public void selectMenJeans(){
        menJeans.click();
}

public void clickKidsCategory()
{
    kidCategory.click();
}

public TShirtCategoryPage selectTshirtCategory() {
    tShirtCategory.click();
    TShirtCategoryPage tShirtPage = new TShirtCategoryPage(driver);
    return tShirtPage;
}

public String getCategoryTitle() {
    return categoryTitle.getText();
}

public void scrollToRecommendedItems() {
    scrollToElement(recommendedItemsArea);
}

public String getRecommendedItemsText() {
    return recommendedItemsText.getText();
}

public List<WebElement> getRecProdList() {
    return recommendedProdList;
}

public void addRecommendedProdToCart(String recProd) {
    // List<WebElement> recommendedProdList = getRecProdList();
    WebElement selectedProd = getRecProdList().stream().filter(prod ->
            prod.findElement(prodName).getText().equalsIgnoreCase(recProd)).findFirst().orElse(null);
    waitForVisibilityOfElement(selectedProd);
    waitForVisibilityOfElement(selectedProd.findElement(addToCartBtn));
    selectedProd.findElement(addToCartBtn).click();
    waitForVisibilityOfElement(continueShoppingBtn);


}

public CartPage viewCart() {
    waitForVisibilityOfElement(viewCartLink);
    viewCartLink.click();
    CartPage cartPage = new CartPage(driver);
    return cartPage;

}

public String getCarouselText() {
    return carouselText.getText();}
}

