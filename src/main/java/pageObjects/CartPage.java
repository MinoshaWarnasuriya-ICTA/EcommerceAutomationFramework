package pageObjects;

import abstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import java.util.*;

public class CartPage extends AbstractComponent {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//tbody/tr/td[2]/h4/a")
    List<WebElement> cartPageProdNames;

    @FindBy(xpath = "//td[@class='cart_price']/p")
    List<WebElement> cartPrice;

    @FindBy(css = ".cart_quantity button")
    List<WebElement> quantityBtn;

    @FindBy(css = ".cart_total_price")
    List<WebElement> totalPriceList;

    @FindBy(css = ".breadcrumb li:nth-child(2)")
    WebElement shoppingCartText;

    @FindBy(xpath = "//*[contains(text(),'Proceed To Checkout')]")
    WebElement checkoutBtn;

    @FindBy(xpath = "//*[@class='modal-content']/div[2]/p[2]/a")
    WebElement loginRegisterLink;

   By removeBtn = By.xpath("//tbody/tr/td[6]/a");


    @FindBy(xpath = "//tbody/tr")
    List<WebElement> cartProdsList;

    By cartProdName = By.xpath("//tbody/tr/td[2]/h4/a");

    public boolean verifyCartProdNames(List<String> addedProds) {
        boolean flag = false;

        for(String addedProd:addedProds)
        {
            for(WebElement cartProd:cartPageProdNames)
            {
                if(cartProd.getText().equalsIgnoreCase(addedProd))
                {
                    flag=true;
                }
                else{
                    flag=false;
                }
            }
        }
        return flag;


    }

    public boolean verifyPrice(List<String> priceList) {
        int k;
        boolean flag = false;
        for (k = 0; k < priceList.size(); k++) {
            for (int l = 0; l < cartPrice.size(); l++) {
                if (priceList.get(k).equalsIgnoreCase(cartPrice.get(l).getText())) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
        }
        return flag;
    }

    public boolean verifyQuantity(String quantity) {
        return quantityBtn.stream().anyMatch(e -> e.getText().equals(quantity));
    }

    public boolean verifyTotalPrice(List<String> priceList) {
        boolean totalMatch = false;
        for (int i = 0; i < priceList.size(); i++) {
            for (int j = 0; j < quantityBtn.size(); j++) {
                int priceInt = Integer.parseInt(priceList.get(i).split(" ")[1]);
                int quantityInt = Integer.parseInt(quantityBtn.get(j).getText());
                int totalPriceInt = Integer.parseInt(totalPriceList.get(i).getText().split(" ")[1]);
                if (priceInt * quantityInt == totalPriceInt) {
                    totalMatch = true;
                }
                else{totalMatch=false;}
            }
        }
        return totalMatch;
    }

    public boolean verifyCartProdByName(String product)
    {
        boolean match= cartPageProdNames.stream().anyMatch(e->e.getText().equals(product));
        return match;
        }

    public String getCartPageDisplayText()
    {
      return  shoppingCartText.getText();

    }

    public LoginPage clickCheckoutToLoginOrSignUp()
    {
        checkoutBtn.click();
        loginRegisterLink.click();
        return new LoginPage(driver);
    }

    public CheckoutPage goToCheckoutPage()
    {
        checkoutBtn.click();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        return checkoutPage;
    }

    public void removeProds(String prodToRemove){

         WebElement trashProd =  cartProdsList.stream().filter(o->o.findElement(cartProdName).getText().equalsIgnoreCase(prodToRemove)).findFirst().orElse(null);
          scrollToElement(trashProd);
          trashProd.findElement(removeBtn).click();
          waitForElementToDissapear(driver,trashProd);

    }

    public boolean verifyRemovalOfProd(String prodsToRemove) {
        boolean flag = false;
             flag = cartProdsList.stream().noneMatch(h -> h.findElement(cartProdName).getText().equalsIgnoreCase(prodsToRemove));
        return flag;
    }

}


