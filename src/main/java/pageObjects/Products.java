package pageObjects;

import abstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import java.util.*;
import java.util.stream.*;

public class Products extends AbstractComponent {

    WebDriver driver;
    WebElement selectedItem;

    public Products(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".features_items .title")
    WebElement allProductsText;

    @FindBy(css = ".features_items .col-sm-4")
    List<WebElement> prodList;

    @FindBy(xpath = "//a[contains(text(),'View Product')]")
    List<WebElement> viewProdBtnList;

    @FindBy(id = "search_product")
    WebElement searchProdBox;

    @FindBy(id = "submit_search")
    WebElement searchSubmitBtn;

    @FindBy(xpath = "//*[@class='features_items']/h2")
    WebElement searchedProductsTitle;

    @FindBy(css = ".single-products")
    List<WebElement> searchedProdList;

    @FindBy(css = ".btn-success")
    WebElement continueShoppingBtn;

   By productOverlay = By.cssSelector(".product-overlay");


    By price = By.cssSelector(".single-products div h2");

    By searchedProdName = By.cssSelector(".productinfo  p");

    By searchedProdNameOverlay = By.xpath("//div[@class='overlay-content']/p");

    By addToCartBtn = By.cssSelector(".product-overlay div a");

    By viewProductBtn = By.cssSelector(".nav-justified a");

    @FindBy(xpath = "//h2[contains(text(),'Brands')]")
    WebElement brandsTitle;

    public String getAllProductsText() {
        return allProductsText.getText();
    }

    public List<WebElement> getProdList() {
        return prodList;
    }

    public List<WebElement> getSearchedProdList()
    {
        return searchedProdList;
    }

    public ProductsDetail viewFirstProd() {
        viewProdBtnList.get(0).click();
        ProductsDetail prodDetail = new ProductsDetail(driver);
        return prodDetail;
    }

    public void searchProd(String prodName) {
        searchProdBox.sendKeys(prodName);
        searchSubmitBtn.click();
    }

    public String getSearchProdText() {
        String searchProdText = searchedProductsTitle.getText();
        return searchProdText;
    }

    public boolean checkVisibilityOfRelatedProds(String prodName) {
        return searchedProdList.stream().anyMatch(p -> p.findElement(searchedProdName).getText().contains(prodName));

    }

    public List<String> addProdsToCartFromList(List prodIndexList) {

        scrollWindow(500);
        List<String> addedProds = new ArrayList<String>();
        for (int i = 0; i < prodIndexList.size(); i++) {
            selectedItem = getProdList().get(i);

            mouseHover(driver, selectedItem);
            selectedItem.findElement(addToCartBtn).click();
            waitForVisibilityOfElement(continueShoppingBtn);
            continueShoppingBtn.click();
            //capture names of added products to a list
            addedProds.add(selectedItem.findElement(searchedProdName).getText());
        }

        return addedProds;

    }


    public List<String> getProdPrice(int[] prodIndex) {

        List<String> priceList = new ArrayList<String>();
        for (int i = 0; i < prodIndex.length; i++) {
            priceList.add(prodList.get(i).findElement(price).getText());
        }
        return priceList;
    }

    public WebElement getProduct(String productName) {
        WebElement selectedProd = getProdList().stream().filter(s -> s.
                findElement(By.cssSelector(".productinfo p"))
                .getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
        return selectedProd;
    }

    public ProductsDetail goToProdDetailPage(String productName) {
        WebElement selectedProd = getProduct(productName);
        scrollWindow(500);
        selectedProd.findElement(viewProductBtn).click();
        ProductsDetail productsDetail = new ProductsDetail(driver);
        return productsDetail;
    }

    public void addProdsToCartByName(String product)
    {
            List<WebElement> choosenProds = getProdList().stream().filter(prod -> prod.findElement(searchedProdName).getText().equals(product)).collect(Collectors.toList());
            for (int i = 0; i < choosenProds.size(); i++) {
                scrollWindow(1000);
                waitForVisibilityOfElement(choosenProds.get(i));
                mouseHover(driver, choosenProds.get(i));
                choosenProds.get(i).findElement(addToCartBtn).click();
                waitForVisibilityOfElement(continueShoppingBtn);
                continueShoppingBtn.click();
            }
    }


    public void addProdListToCart(String[] productList) {

        List<String> productsList = Arrays.asList(productList);
        scrollWindow(850);
                for(int i=0;i<productsList.size();i++) {
                    int finalI = i;
                    WebElement choosenProd = prodList.stream().filter(p -> p.findElement(searchedProdName).getText().contains(productsList.get(finalI))).findFirst().orElse(null);
                    mouseHover(driver, choosenProd);
                    waitForVisibilityOfElement(choosenProd.findElement(addToCartBtn));
                    choosenProd.findElement(addToCartBtn).click();
                    waitForVisibilityOfElement(continueShoppingBtn);
                    continueShoppingBtn.click();

                }
    }

    public String getBrandsTitle()
    {
      return  brandsTitle.getText();
    }

    public BrandPage goToBrandPage(String brand)
    {
        driver.findElement(By.xpath("//*[text() ='"+brand+"']")).click();
        BrandPage brandPage = new BrandPage(driver);
        return brandPage;
    }

    public List<String> addAllSearchedProdsToCart() throws InterruptedException {

        List<String> addedProdList =new ArrayList<String>();
       for(WebElement prod:searchedProdList)
       {
            scrollToElement(prod);
           addedProdList.add(prod.findElement(searchedProdName).getText());
           Thread.sleep(1000);
           mouseHover(driver,prod);
           waitForVisibilityOfElement(prod.findElement(productOverlay));
           waitForVisibilityOfElement(prod.findElement(addToCartBtn));
           prod.findElement(addToCartBtn).click();
           waitForVisibilityOfElement(continueShoppingBtn);
           continueShoppingBtn.click();

       }
       return addedProdList;
    }


    public ProductsDetail viewSelectedProd(String prodName) {

        WebElement selectedProd =  getProduct(prodName);
        scrollToElement(selectedProd);
        waitForVisibilityOfElement(selectedProd);
       selectedProd.findElement(viewProductBtn).click();
        ProductsDetail productsDetail = new ProductsDetail(driver);
        return productsDetail;
    }
}

